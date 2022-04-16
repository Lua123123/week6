package com.team12.android_challenge_w6.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ClientRetrofit {
    private var api : ServiceRetrofit

    val API: ServiceRetrofit
        get() = api

    init {
        api = createMovieDBService()
    }

    companion object {
        private var mInstance : ClientRetrofit? = null
        fun getInstance() = mInstance ?: synchronized(this){
            mInstance ?: ClientRetrofit().also { mInstance = it }
        }
    }

    private fun createMovieDBService(): ServiceRetrofit{
        //create okhttp
        val httpClient = OkHttpClient.Builder()
                .addInterceptor(Authentication())
                .build()

        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build()

        return retrofit.create(ServiceRetrofit::class.java)
    }
}