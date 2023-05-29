package com.peonlee.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.peonlee.login.databinding.ActivityLoginBinding
import com.peonlee.login.extensions.isKakaoTalkLoginAvailable
import com.peonlee.login.extensions.loginWithKakaoAccount

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var googleSignInLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun googleLogin() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        val signInIntent = mGoogleSignInClient.signInIntent
        googleSignInLauncher.launch(signInIntent)
    }

    private fun googleSignInResult() {
        googleSignInLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            when(result.resultCode) {
                Activity.RESULT_OK -> {
                    val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                    handleSignInResult(task)
                }
                else -> Toast.makeText(this, "구글 로그인 실패", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            Toast.makeText(this, "구글 로그인 성공", Toast.LENGTH_LONG).show()
            val account: GoogleSignInAccount = completedTask.getResult(ApiException::class.java)
            Log.d("구글 Success : ", account.idToken.toString())
            Log.d("구글 Success : ", account.id.toString())
            Log.d("구글 Success : ", account.email.toString())
        } catch (e: ApiException) {
            Toast.makeText(this, "구글 로그인 실패", Toast.LENGTH_LONG).show()
            Log.d("구글 Fail", "signInResult:failed code=" + e.statusCode)
        }
    }

    private fun kakaoLogin() {
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            when {
                error != null -> Log.e("카카오 계정 로그인 실패", "카카오 계정 로그인 실패", error)
                token != null -> Log.i("카카오 계정 로그인 성공", "카카오 계정 로그인 성공 ${token.accessToken}")
            }
        }

        if (isKakaoTalkLoginAvailable()) {
            UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                when {
                    error != null -> {
                        Log.e("카카오톡 로그인 실패", "카카오톡 로그인 실패", error)

                        val userLoginCancel = error is ClientError && error.reason == ClientErrorCause.Cancelled
                        if (userLoginCancel) {
                            return@loginWithKakaoTalk
                        }
                        loginWithKakaoAccount(callback = callback)
                    }
                    token != null -> Log.i("카카오톡 로그인 성공", "카카오톡으로 로그인 성공 ${token.accessToken}")
                }
            }
        } else {
            loginWithKakaoAccount(callback = callback)
        }
    }

    private fun init() {
        googleSignInResult()

        binding.tvGoogleLogin.setOnClickListener {
            googleLogin()
        }

        binding.tvKakaoLogin.setOnClickListener {
            kakaoLogin()
        }
    }
}