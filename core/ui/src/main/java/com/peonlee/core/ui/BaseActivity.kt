
package com.peonlee.core.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }
}
