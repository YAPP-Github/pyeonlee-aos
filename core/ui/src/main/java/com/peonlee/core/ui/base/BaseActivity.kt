package com.peonlee.core.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import coil.Coil
import coil.ImageLoader
import coil.disk.DiskCache
import coil.memory.MemoryCache

abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {
    private lateinit var _binding: T

    val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val imageLoader = ImageLoader.Builder(this)
            .memoryCache {
                MemoryCache.Builder(this)
                    .maxSizePercent(0.25)
                    .build()
            }
            .diskCache {
                DiskCache.Builder()
                    .directory(this.cacheDir.resolve("image_cache"))
                    .maxSizePercent(0.02)
                    .build()
            }
            .build()
        Coil.setImageLoader(imageLoader)

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
