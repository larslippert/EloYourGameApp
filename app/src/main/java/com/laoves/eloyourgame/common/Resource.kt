package com.laoves.eloyourgame.common

sealed class Resource<T>(val data: T? = null, val code: T?, val message: String? = null) {
    class Success<T>(data: T, code: T? = null) : Resource<T>(data, code)
    class Error<T>(message: String, data: T? = null, code: T? = null) : Resource<T>(data, code, message)
    class Loading<T>(data: T? = null, code: T? = null) : Resource<T>(data, code)
}