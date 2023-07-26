
package com.peonlee.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.peonlee.R
import com.peonlee.data.local.LocalDataSource
import com.peonlee.login.LoginActivity
import com.peonlee.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    @Inject lateinit var dataStore: LocalDataSource
    private var isReady = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_splash)
        delayScreen()
    }

    private fun delayScreen() {
        Handler(Looper.getMainLooper()).postDelayed({ isReady = true }, DELAY)

        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    return if (isReady) {
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        getToken()
                        true
                    } else {
                        false
                    }
                }
            }
        )
    }

    private fun getToken() {
        lifecycleScope.launch {
            dataStore.getAccessToken().collect { accessToken ->
                if (accessToken.isNotEmpty()) {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                } else {
                    startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                }
                finish()
            }
        }
    }

    companion object {
        const val DELAY = 1000L
    }
}
