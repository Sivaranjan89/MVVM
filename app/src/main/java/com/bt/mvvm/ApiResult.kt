package com.bt.mvvm

sealed class ApiResult {
    data class onSuccess(val data : Any) : ApiResult()
    data class onError(val error : Throwable) : ApiResult()
}