package com.higor.restaurantautomation.domain.service.exception

import org.springframework.http.HttpStatus.CONFLICT

class ResourceAlreadyExists(message: String) : CustomException(message) {
    override val statusCode = CONFLICT

    companion object {
        private const val serialVersionUID = 1L
    }
}
