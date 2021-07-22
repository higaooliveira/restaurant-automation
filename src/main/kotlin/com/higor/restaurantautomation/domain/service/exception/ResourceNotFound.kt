package com.higor.restaurantautomation.domain.service.exception

import org.springframework.http.HttpStatus.NOT_FOUND

data class ResourceNotFound(override val message: String) : CustomException(message) {
    override val statusCode = NOT_FOUND
}
