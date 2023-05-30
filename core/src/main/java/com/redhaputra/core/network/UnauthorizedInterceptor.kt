package com.redhaputra.core.network

import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.Response

class UnauthorizedInterceptor(
    moshi: Moshi
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val mainRequest = chain.request().newBuilder()
            .addHeader(
                "Content-Type",
                "application/json"
            ).build()
        return chain.proceed(mainRequest)
    }
}