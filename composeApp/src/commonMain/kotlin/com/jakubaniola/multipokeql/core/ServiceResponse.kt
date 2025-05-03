package com.jakubaniola.multipokeql.core

sealed interface ServiceResponse<T> {
    data class Success<T>(val data: T) : ServiceResponse<T>
    data class Error<T>(val message: String? = null) : ServiceResponse<T>
}