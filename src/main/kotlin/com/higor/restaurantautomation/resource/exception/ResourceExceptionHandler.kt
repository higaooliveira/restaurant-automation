package com.higor.restaurantautomation.resource.exception

import com.higor.restaurantautomation.domain.dto.StandardError
import com.higor.restaurantautomation.domain.service.exception.ResourceAlreadyExists
import com.higor.restaurantautomation.domain.service.exception.ResourceNotFound
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import java.io.IOException
import java.time.Instant
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ResourceExceptionHandler {
    val messageList: HashSet<String> = HashSet()
    lateinit var error: StandardError

    @ExceptionHandler(ResourceNotFound::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun resourceNotFound(ex: ResourceNotFound, request: HttpServletRequest): ResponseEntity<StandardError> {
        this.messageList.add(ex.message!!)
        this.error = StandardError(this.messageList, request.requestURI, Instant.now())

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.error)
    }

    @ExceptionHandler(ResourceAlreadyExists::class)
    @ResponseStatus(HttpStatus.CONFLICT)
    fun resourceAlreadyExists(ex: ResourceAlreadyExists, request: HttpServletRequest): ResponseEntity<StandardError> {
        this.messageList.add(ex.message!!)
        this.error = StandardError(this.messageList, request.requestURI, Instant.now())

        return ResponseEntity.status(HttpStatus.CONFLICT).body(this.error)
    }

    @ExceptionHandler(IOException::class)
    @ResponseStatus(HttpStatus.CONFLICT)
    fun ioException(ex: IOException, request: HttpServletRequest): ResponseEntity<StandardError> {
        this.messageList.add(ex.message!!)
        this.error = StandardError(this.messageList, request.requestURI, Instant.now())

        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(this.error)
    }
}
