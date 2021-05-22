package com.higor.restaurantautomation.domain.service.exception

import org.springframework.http.HttpStatus.BAD_REQUEST

class BadRequestException(message: String): CustomException(message) {
    override val statusCode = BAD_REQUEST
}