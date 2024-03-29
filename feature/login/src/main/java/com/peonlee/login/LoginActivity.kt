package com.peonlee.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.peonlee.board.OnboardActivity
import com.peonlee.core.ui.base.BaseActivity
import com.peonlee.core.ui.extensions.showToast
import com.peonlee.feature.terms.TermsActivity
import com.peonlee.login.databinding.ActivityLoginBinding
import com.peonlee.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    private val googleSignInLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            googleSignInResult(activityResult)
        }

    private val agreeTermsLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            termsResult(activityResult)
        }

    private val loginViewModel: LoginViewModel by viewModels()

    override fun bindingFactory() = ActivityLoginBinding.inflate(layoutInflater)

    override fun initViews() = with(binding) {
        loginObserve()
        tvGoogleLogin.setOnClickListener { googleLogin() }
        tvKakaoLogin.setOnClickListener { kakaoLogin() }
    }

    private fun loginObserve() {
        lifecycleScope.launch {
            loginViewModel.loginState.collect { loginState ->
                when (loginState) {
                    is LoginState.Init -> Unit
                    is LoginState.Success -> {
                        loginViewModel.setToken(loginState.data.accessToken)
                        val clazz = if (loginViewModel.signUpFlag) OnboardActivity::class.java else MainActivity::class.java
                        startActivity(Intent(this@LoginActivity, clazz))
                        finish()
                    }

                    is LoginState.NotRegistered -> {
                        val intent = Intent(this@LoginActivity, TermsActivity::class.java)
                        agreeTermsLauncher.launch(intent)
                    }

                    is LoginState.Fail -> showToast(R.string.server_error)
                }
            }
        }
    }

    private fun googleLogin() {
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(BuildConfig.GOOGLE_CLIENT_ID)
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)
        val signInIntent = googleSignInClient.signInIntent
        googleSignInLauncher.launch(signInIntent)
    }

    private fun googleSignInResult(activityResult: ActivityResult) {
        when (activityResult.resultCode) {
            Activity.RESULT_OK -> {
                val task = GoogleSignIn.getSignedInAccountFromIntent(activityResult.data)
                task.addOnCompleteListener { result ->
                    if (result.isSuccessful) {
                        task.result.idToken?.let { googleIdToken ->
                            loginViewModel.login(googleIdToken, "GOOGLE")
                        } ?: showToast(getString(R.string.login_failed))
                    } else {
                        showToast(getString(R.string.login_failed))
                    }
                }
            }
            else -> showToast(getString(R.string.login_failed))
        }
    }

    private fun kakaoLogin() {
        val isKakaoTalkLoginAvailable = UserApiClient.instance.isKakaoTalkLoginAvailable(this)
        if (isKakaoTalkLoginAvailable) {
            UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                when {
                    error != null -> {
                        val isUserLoginCancel = error is ClientError && error.reason == ClientErrorCause.Cancelled
                        if (isUserLoginCancel) {
                            return@loginWithKakaoTalk
                        }
                        loginWithKakaoAccount()
                    }
                    token != null -> {
                        token.idToken?.let { idToken ->
                            loginViewModel.login(idToken, "KAKAO")
                        } ?: showToast(R.string.invalid_token)
                    }
                }
            }
        } else {
            loginWithKakaoAccount()
        }
    }

    private fun loginWithKakaoAccount() {
        val kakaoLoginCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            when {
                error != null -> showToast(getString(R.string.login_failed))
                token != null -> token.idToken?.let { idToken -> loginViewModel.login(idToken, "KAKAO") }
            }
        }
        return UserApiClient.instance.loginWithKakaoAccount(this, callback = kakaoLoginCallback)
    }

    private fun termsResult(activityResult: ActivityResult) {
        when (activityResult.resultCode) {
            Activity.RESULT_OK -> {
                loginViewModel.setSignUpFlag()
                loginViewModel.signUp()
            }
            else -> Unit
        }
    }

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(
                Intent(context, LoginActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
            )
        }
    }
}
