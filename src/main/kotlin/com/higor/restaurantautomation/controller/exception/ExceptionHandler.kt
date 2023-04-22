package com.higor.restaurantautomation.controller.exception

import com.higor.restaurantautomation.domain.dto.StandardError
import com.higor.restaurantautomation.domain.service.exception.ApiException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.Instant
import jakarta.servlet.http.HttpServletRequest

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(ApiException::class)
    fun handle(
        ex: ApiException,
        request: HttpServletRequest
    ): ResponseEntity<StandardError> {
        return ResponseEntity
            .status(ex.statusCode)
            .body(standardErrorFactory(ex.message!!, request))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentException(
        ex: MethodArgumentNotValidException,
        request: HttpServletRequest
    ): ResponseEntity<StandardError> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(standardErrorFactory("Invalid Request Body", request))
    }

    private fun standardErrorFactory(message: String, request: HttpServletRequest): StandardError {
        val messages = HashSet<String>()
        messages.add(message)
        return StandardError(messages, request.requestURI, Instant.now())
    }
}
