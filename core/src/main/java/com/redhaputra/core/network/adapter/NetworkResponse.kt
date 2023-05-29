package com.redhaputra.core.network.adapter

/**
 * Generic network response
 * @param S success body response
 * @param F failure body response
 */
//sealed class NetworkResponse<out S : Any, out F : Any> {
//    /**
//     * Success response with body
//     */
//    data class Success<S : Any>(val body: S) : NetworkResponse<S, Nothing>()
//
//    /**
//     * Failure response
//     */
//    data class Error<F : Any>(val body: F, val code: Int) : NetworkResponse<Nothing, F>()
//}
sealed class NetworkResponse<out T : Any> {

    /**
     * Handle resource success
     */
    class Success<out T : Any>(val data: T?) : NetworkResponse<T>()

    /**
     * Handle resource error
     */
    class Error(val message: String, val errorCode: Int = -1) :
        NetworkResponse<Nothing>()

    override fun toString(): String =
        when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error $message"
        }
}
