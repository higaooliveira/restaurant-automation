package com.higor.restaurantautomation.utils.extensions

import com.higor.restaurantautomation.domain.dto.StandardError
import com.higor.restaurantautomation.domain.service.exception.ApiErrorCodes
import com.higor.restaurantautomation.utils.messageSource

fun StandardError.Companion.from(error: ApiErrorCodes, errorFields: Set<String>? = null): StandardError {
    return StandardError(
        code = error.code,
        message = messageSource(error.key),
        errorFields = errorFields,
    )
}
