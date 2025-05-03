package com.jakubaniola.multipokeql.core

sealed interface DataResult<T> {
    data class Success<T>(val data: T) : DataResult<T>
    data class Error<T>(val message: String? = null) : DataResult<T>
}

fun <T> ServiceResponse<T>.toDataResult(): DataResult<T> {
    return when (this) {
        is ServiceResponse.Success -> DataResult.Success(data)
        is ServiceResponse.Error -> DataResult.Error(message)
    }
}

fun <T, Y> DataResult<T>.mapSuccess(map: (T) -> Y): DataResult<Y> {
    return when (this) {
        is DataResult.Success -> DataResult.Success(map(data))
        is DataResult.Error -> DataResult.Error(message)
    }
}