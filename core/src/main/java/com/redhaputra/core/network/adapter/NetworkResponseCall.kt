package com.redhaputra.core.network.adapter

import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response

/**
 * Wrapper of network response call
 */
//class NetworkResponseCall<S : Any, E : Any>(
//    private val delegate: Call<S>,
//    private val errorConverter: Converter<ResponseBody, E>
//) : Call<NetworkResponse<S, E>> {
//    override fun clone(): Call<NetworkResponse<S, E>> =
//        NetworkResponseCall(delegate.clone(), errorConverter)
//
//    override fun execute(): Response<NetworkResponse<S, E>> {
//        throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
//    }
//
//    override fun isExecuted(): Boolean = delegate.isExecuted
//
//    override fun cancel() = delegate.cancel()
//
//    override fun isCanceled(): Boolean = delegate.isCanceled
//
//    override fun request(): Request = delegate.request()
//
//    override fun timeout(): Timeout = delegate.timeout()
//
//    override fun enqueue(callback: Callback<NetworkResponse<S, E>>) =
//        delegate.enqueue(object : Callback<S> {
//            override fun onResponse(call: Call<S>, response: Response<S>) {
//                val body = response.body()
//                val code = response.code()
//                val error = response.errorBody()
//                if (response.isSuccessful) {
//                    if (body != null) {
//                        callback.onResponse(
//                            this@NetworkResponseCall,
//                            Response.success(NetworkResponse.Success(body))
//                        )
//                    } else {
//                        // Response is successful but the body is null
//                        callback.onResponse(
//                            this@NetworkResponseCall,
//                            Response.success(NetworkResponse.UnknownError(null))
//                        )
//                    }
//                } else {
//                    val errorBody = when {
//                        error == null -> null
//                        error.contentLength() == 0L -> null
//                        else ->
//                            try {
//                                errorConverter.convert(error)
//                            } catch (ex: Exception) {
//                                null
//                            }
//                    }
//                    if (errorBody != null) {
//                        callback.onResponse(
//                            this@NetworkResponseCall,
//                            Response.success(NetworkResponse.ApiError(errorBody, code))
//                        )
//                    } else {
//                        callback.onResponse(
//                            this@NetworkResponseCall,
//                            Response.success(NetworkResponse.UnknownError(null))
//                        )
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<S>, throwable: Throwable) {
//                val networkResponse = when (throwable) {
//                    is JsonEncodingException -> {
//                        val throwJson = Throwable("Response is not valid json")
//                        NetworkResponse.UnknownError(throwJson)
//                    }
//                    is IOException -> NetworkResponse.NetworkError(throwable)
//                    else -> NetworkResponse.UnknownError(throwable)
//                }
//                callback.onResponse(this@NetworkResponseCall, Response.success(networkResponse))
//            }
//        })
//}