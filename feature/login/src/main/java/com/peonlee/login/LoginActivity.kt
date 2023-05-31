package com.peonlee.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.peonlee.core.ui.BaseActivity
import com.peonlee.login.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<ActivityLoginBinding>({ layoutInflater ->
    ActivityLoginBinding.inflate(layoutInflater)
}) {
    private val googleSignInLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            googleSignInResult(activityResult)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
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
                        // TODO : 성공 로직(로그인 성공) : ViewModel 연동
                    } else {
                        // TODO : 실패 로직(로그인 실패) 수행
                    }
                }
            }

            else -> {
                // TODO : 실패로직 수행
            }
        }
    }

    private fun kakaoLogin() {
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            when {
                error != null -> {
                    // TODO : 실패로직 수행
                    Log.e("카카오 계정 로그인 실패", "카카오 계정 로그인 실패", error)
                }

                token != null -> {
                    // TODO : 성공로직 수행
                    Log.i("카카오 계정 로그인 성공", "카카오 계정 로그인 성공 ${token.accessToken}")
                }
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

                    token != null -> {
                        // TODO : 성공로직 수행
                    }
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
