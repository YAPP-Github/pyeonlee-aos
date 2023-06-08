package com.peonlee.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.peonlee.core.ui.BaseActivity
import com.peonlee.core.ui.extensions.showToast
import com.peonlee.login.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    private val googleSignInLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            googleSignInResult(activityResult)
        }

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun bindingFactory() = ActivityLoginBinding.inflate(layoutInflater)

    override fun initViews() = with(binding) {
        tvGoogleLogin.setOnClickListener { googleLogin() }
        tvKakaoLogin.setOnClickListener { kakaoLogin() }
    }

    private fun googleLogin() {
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(BuildConfig.GOOGLE_CLIENT_ID)
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
                            loginViewModel.snsLogin(googleIdToken)
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
                    token != null -> loginViewModel.snsLogin(token.accessToken)
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
                token != null -> loginViewModel.snsLogin(token.accessToken)
            }
        }
        return UserApiClient.instance.loginWithKakaoAccount(this, callback = kakaoLoginCallback)
    }
}
