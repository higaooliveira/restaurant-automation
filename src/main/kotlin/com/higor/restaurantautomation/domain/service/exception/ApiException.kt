package com.higor.restaurantautomation.domain.service.exception

import org.springframework.http.HttpStatus

class ApiException(message: String, val statusCode: HttpStatus) : Exception(message)
