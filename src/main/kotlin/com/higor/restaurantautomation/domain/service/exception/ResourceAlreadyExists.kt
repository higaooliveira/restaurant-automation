package com.higor.restaurantautomation.domain.service.exception

import org.springframework.http.HttpStatus.CONFLICT

data class ResourceAlreadyExists(override val message: String) : CustomException(message) {
    override val statusCode = CONFLICT
}
