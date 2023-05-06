package com.higor.restaurantautomation.domain.dto

import com.higor.restaurantautomation.domain.service.exception.ApiErrorCodes
import com.higor.restaurantautomation.utils.messageSource

data class StandardError(
    val code: String,
    val message: String,
    val details: String? = null,
    val errorFields: Set<String>? = null,
) {
    companion object {
        fun from(error: ApiErrorCodes, errorFields: Set<String>? = null): StandardError {
            return StandardError(
                code = error.code,
                message = messageSource(error.key),
                errorFields = errorFields,
            )
        }
    }
}
