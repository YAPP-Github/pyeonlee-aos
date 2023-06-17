package com.peonlee.core.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {
    private lateinit var _binding: T

    val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingFactory()
        setContentView(binding.root)
        initViews()
        bindViews()
    }

    abstract fun bindingFactory(): T
    // view 와 model binding
    open fun initViews() {}

    // view 에 event listener binding
    open fun bindViews() {}
}
