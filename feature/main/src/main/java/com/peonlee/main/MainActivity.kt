package com.peonlee.main

import android.os.Bundle
import androidx.fragment.app.commit
import com.peonlee.core.ui.base.BaseActivity
import com.peonlee.home.HomeFragment
import com.peonlee.main.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO Navigation 적용 Issue 작업 에서 수정할 예정
        supportFragmentManager.commit {
            add(binding.layoutFragment.id, HomeFragment.getInstance())
        }
    }

    override fun bindingFactory(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
}
