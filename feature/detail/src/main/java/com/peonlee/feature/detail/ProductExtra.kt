package com.peonlee.feature.detail

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductExtra(
    val id: Int,
    val imageUrl: String,
    val name: String,
    val price: Int
) : Parcelable
