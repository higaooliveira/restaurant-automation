package com.higor.restaurantautomation.controller.exception

import com.higor.restaurantautomation.domain.dto.StandardError
import com.higor.restaurantautomation.domain.service.exception.ApiException
import jakarta.persistence.EntityNotFoundException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.Instant

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(ApiException::class)
    fun handle(
        ex: ApiException,
        request: HttpServletRequest,
    ): ResponseEntity<StandardError> {
        return ResponseEntity
            .status(ex.statusCode)
            .body(standardErrorFactory(setOf(ex.localizedMessage), request))
    }

    @ExceptionHandler(EntityNotFoundException::class)
    fun entityNotFoundException(
        ex: EntityNotFoundException,
        request: HttpServletRequest,
    ): ResponseEntity<StandardError> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(standardErrorFactory(setOf("Resource not found"), request))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentException(
        ex: MethodArgumentNotValidException,
        request: HttpServletRequest,
    ): ResponseEntity<StandardError> {
        val errors = ex.bindingResult.fieldErrors.map { it.defaultMessage!! }.toSet()
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(standardErrorFactory(errors, request))
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun httpMessageNotReadableException(
        ex: HttpMessageNotReadableException,
        request: HttpServletRequest,
    ): ResponseEntity<StandardError> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(standardErrorFactory(setOf("Invalid Request Body"), request))
    }

    private fun standardErrorFactory(messages: Set<String>, request: HttpServletRequest): StandardError {
        return StandardError(messages, request.requestURI, Instant.now())
    }
}
