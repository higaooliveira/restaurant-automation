package com.higor.restaurantautomation.controller.auth

import com.higor.restaurantautomation.domain.dto.auth.AuthenticationDtoIn
import com.higor.restaurantautomation.domain.dto.auth.AuthenticationDtoOut
import com.higor.restaurantautomation.domain.dto.auth.RegisterDto
import com.higor.restaurantautomation.domain.service.auth.AuthenticationService
import com.higor.restaurantautomation.domain.service.auth.RegisterService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(AuthenticationController.BASE_ENDPOINT)
@Validated
class AuthenticationController(
    private val registerService: RegisterService,
    private val authenticationService: AuthenticationService,
) {

    @PostMapping("/register")
    fun register(
        @Valid @RequestBody
        request: RegisterDto,
    ): ResponseEntity<AuthenticationDtoOut> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(registerService.execute(request))
    }

    @PostMapping("/authenticate")
    fun authenticate(
        @RequestBody request: AuthenticationDtoIn,
    ): ResponseEntity<AuthenticationDtoOut> {
        return ResponseEntity.ok(authenticationService.execute(request))
    }

    companion object {
        const val BASE_ENDPOINT = "/api/v1/office/auth"
    }
}
