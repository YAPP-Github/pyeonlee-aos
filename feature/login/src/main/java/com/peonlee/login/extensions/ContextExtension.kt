package com.peonlee.login.extensions

import android.content.Context
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient

fun Context.loginWithKakaoAccount(callback: (token: OAuthToken?, error: Throwable?) -> Unit) {
    return UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
}

fun Context.isKakaoTalkLoginAvailable(): Boolean {
    return UserApiClient.instance.isKakaoTalkLoginAvailable(this)
}