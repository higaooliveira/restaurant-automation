package com.higor.restaurantautomation.domain.service.exception

class ResourceNotFound(message: String) : Exception(message) {
    companion object {
        private const val serialVersionUID = 1L
    }
}