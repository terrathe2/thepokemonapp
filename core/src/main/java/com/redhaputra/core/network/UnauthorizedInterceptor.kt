package com.redhaputra.core.network

import okhttp3.Interceptor
import okhttp3.Response

class UnauthorizedInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val mainRequest = chain.request().newBuilder()
            .addHeader(
                "Content-Type",
                "application/json"
            ).build()
        return chain.proceed(mainRequest)
    }
}