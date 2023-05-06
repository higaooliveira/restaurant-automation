package com.higor.restaurantautomation.domain.service.exception

import com.higor.restaurantautomation.utils.messageSource

open class CustomException(
    val status: Int,
    val code: String,
    open val key: String,
    open val params: Array<String> = emptyArray(),
    open val details: String? = null,
    cause: Throwable? = null,
    var errors: Array<String>? = null,
    override var message: String? = messageSource(key = key, params = params),
) : RuntimeException(message)

class ApiException(
    errorCode: ApiErrorCodes,
    cause: Throwable? = null,
    params: Array<String> = emptyArray(),
    errors: Array<String>? = null,
) : CustomException(
    status = errorCode.httpStatus.value(),
    code = errorCode.code,
    key = errorCode.key,
    params = params,
    details = cause?.message,
    cause = cause,
    errors = errors,
)
