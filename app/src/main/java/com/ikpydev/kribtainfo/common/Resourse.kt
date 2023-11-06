package com.ikpydev.kribtainfo.common

sealed class Resourse<T>(data: T?, message: String? = null) {
    data class Seccesss<T>(val data: T?) : Resourse<T>(data)
    data class Error<T>(val data: T? = null, val message: String?) : Resourse<T>(data, message)
}
