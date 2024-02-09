package com.example.moviesapp.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class MovieApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url

        //@Query("api_key") apiKey: String = "b6c84267a24780c397d359579bb6ad19",
        val url = originalHttpUrl
            .newBuilder()
            .addQueryParameter("api_key","b6c84267a24780c397d359579bb6ad19")
            .build()

        val request = originalRequest.newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }
}