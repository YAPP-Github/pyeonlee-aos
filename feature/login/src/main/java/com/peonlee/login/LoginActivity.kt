package com.peonlee.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
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
class LoginActivity : BaseActivity<ActivityLoginBinding>({ layoutInflater ->
    ActivityLoginBinding.inflate(layoutInflater)
}) {
    private val googleSignInLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            googleSignInResult(activityResult)
        }

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
    }

    override fun splashLoad() {
        installSplashScreen()
    }

    private fun initViews() = with(binding) {
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
                        val googleIdToken: String = task.result.idToken ?: ""
                        loginViewModel.snsLogin(googleIdToken)
                    } else {
                        showToast(getString(R.string.login_failed))
                    }
                }
            }

            else -> showToast(getString(R.string.login_failed))
        }
    }

    private fun kakaoLogin() {
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            when {
                error != null -> showToast(getString(R.string.login_failed))
                token != null -> loginViewModel.snsLogin(token.accessToken)
            }
        }

        if (isKakaoTalkLoginAvailable()) {
            UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                when {
                    error != null -> {
                        val isUserLoginCancel = error is ClientError && error.reason == ClientErrorCause.Cancelled
                        if (isUserLoginCancel) {
                            return@loginWithKakaoTalk
                        }
                        loginWithKakaoAccount(callback = callback)
                    }

                    token != null -> showToast(getString(R.string.login_failed))
                }
            }
        } else {
            loginWithKakaoAccount(callback = callback)
        }
    }

    private fun Context.loginWithKakaoAccount(callback: (token: OAuthToken?, error: Throwable?) -> Unit) {
        return UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
    }

    private fun Context.isKakaoTalkLoginAvailable(): Boolean {
        return UserApiClient.instance.isKakaoTalkLoginAvailable(this)
    }
}
