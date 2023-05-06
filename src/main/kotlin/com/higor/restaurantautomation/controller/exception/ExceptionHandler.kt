package com.higor.restaurantautomation.controller.exception

import com.higor.restaurantautomation.domain.dto.StandardError
import com.higor.restaurantautomation.domain.service.exception.ApiErrorCodes
import com.higor.restaurantautomation.domain.service.exception.ApiException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(ApiException::class)
    fun handle(
        ex: ApiException,
        request: HttpServletRequest,
    ): ResponseEntity<StandardError> {
        return ResponseEntity
            .status(ex.status)
            .body(standardErrorFactory(ex.code, ex.details, ex.message!!))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentException(
        ex: MethodArgumentNotValidException,
        request: HttpServletRequest,
    ): ResponseEntity<StandardError> {
        val errors = ex.bindingResult.fieldErrors.map { it.defaultMessage!! }.toSet()
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(StandardError.from(ApiErrorCodes.BAD_REQUEST, errors))
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun httpMessageNotReadableException(
        ex: HttpMessageNotReadableException,
        request: HttpServletRequest,
    ): ResponseEntity<StandardError> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(StandardError.from(ApiErrorCodes.BAD_REQUEST))
    }

    private fun standardErrorFactory(code: String, details: String?, message: String): StandardError {
        return StandardError(
            code = code,
            message = message,
            details = details,
        )
    }
}
