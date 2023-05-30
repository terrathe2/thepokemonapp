package com.redhaputra.core.network.adapter

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * Factory of Network Response Adapter
 */
//class NetworkResponseAdapterFactory : CallAdapter.Factory() {
//
//    override fun get(
//        returnType: Type,
//        annotations: Array<out Annotation>,
//        retrofit: Retrofit
//    ): CallAdapter<*, *>? =
//        // suspend functions wrap the response type in `Call`
//        if (Call::class.java != getRawType(returnType)) {
//            null
//        } else {
//            // check first that the return type is `ParameterizedType`
//            check(returnType is ParameterizedType) {
//                "return type must be parameterized as Call<NetworkResponse<<Foo>> or " +
//                        "Call<NetworkResponse<out Foo>>"
//            }
//
//            // get the response type inside the `Call` type
//            val responseType = getParameterUpperBound(0, returnType)
//            // if the response type is not ApiResponse then we can't handle this type, so we return null
//            if (getRawType(responseType) != NetworkResponse::class.java) {
//                null
//            } else {
//                // the response type is ApiResponse and should be parameterized
//                check(responseType is ParameterizedType) {
//                    "Response must be parameterized as " +
//                            "NetworkResponse<Foo> or NetworkResponse<out Foo>"
//                }
//
//                val successBodyType = getParameterUpperBound(0, responseType)
//                val errorBodyType = getParameterUpperBound(1, responseType)
//
//                val errorBodyConverter =
//                    retrofit.nextResponseBodyConverter<Any>(null, errorBodyType, annotations)
//
//                NetworkResponseAdapter<Any, Any>(successBodyType, errorBodyConverter)
//            }
//        }
//}