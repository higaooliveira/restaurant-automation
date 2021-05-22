package com.higor.restaurantautomation.domain.service.exception

import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR

abstract class CustomException(message: String) : RuntimeException(message) {
    open val statusCode = INTERNAL_SERVER_ERROR
}
