package com.higor.restaurantautomation.domain.service.exception

import org.springframework.http.HttpStatus

enum class ApiErrorCodes(
    val code: String,
    val key: String,
    val httpStatus: HttpStatus,
) {
    USER_EXISTS(
        code = "USR-0000",
        key = "user.exists",
        httpStatus = HttpStatus.CONFLICT,
    ),

    USER_NOT_FOUND(
        code = "USR-0001",
        key = "user.notfound",
        httpStatus = HttpStatus.NOT_FOUND,
    ),

    COMPANY_EXISTS(
        code = "CPN-0000",
        key = "company.exists",
        httpStatus = HttpStatus.CONFLICT,
    ),

    COMPANY_NOT_FOUND(
        code = "CPN-0001",
        key = "company.notfound",
        httpStatus = HttpStatus.NOT_FOUND,
    ),

    BAD_REQUEST(
        code = "REQ-0000",
        key = "bad.request",
        httpStatus = HttpStatus.BAD_REQUEST,
    ),
}
