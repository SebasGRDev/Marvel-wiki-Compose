package com.sebasgrdev.marvelwiki.utils

sealed class Response<T>(val data: T? = null, val message: String? = null) {
    class Loanding<T>(data: T? = null): Response<T>(data)
    class Success<T>(data: T): Response<T>(data)
    class Error<T>(message: String, data: T?= null): Response<T>(data, message)
}