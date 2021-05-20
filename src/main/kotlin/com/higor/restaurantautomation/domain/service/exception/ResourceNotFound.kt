package com.higor.restaurantautomation.domain.service.exception

import org.springframework.http.HttpStatus.NOT_FOUND

class ResourceNotFound(message: String) : CustomException(message) {

    override val statusCode = NOT_FOUND

    companion object {
        private const val serialVersionUID = 1L
    }
}
