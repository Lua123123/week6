package com.team12.android_challenge_w6.network

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


/**
 * Created by nampham on 5/7/21.
 */
class Authentication : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url: HttpUrl = chain.request().url()
                .newBuilder()
                .addQueryParameter("api_key", "f19164b5d87dd836619600b1004d8648")
                .build()

        val request: Request = chain.request().newBuilder().url(url).build()
        return chain.proceed(request)
    }
}