package com.higor.restaurantautomation.controller

import com.higor.restaurantautomation.domain.dto.AuthenticationDtoIn
import com.higor.restaurantautomation.domain.dto.AuthenticationDtoOut
import com.higor.restaurantautomation.domain.dto.RegisterDto
import com.higor.restaurantautomation.domain.service.AuthenticationService
import com.higor.restaurantautomation.domain.service.CompanyRegisterService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/office/auth")
class AuthenticationController(
    private val companyRegisterService: CompanyRegisterService,
    private val authenticationService: AuthenticationService,
) {

    @PostMapping("/register")
    fun register(
        @RequestBody request: RegisterDto,
    ): ResponseEntity<AuthenticationDtoOut> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(companyRegisterService.execute(request))
    }

    @PostMapping("/authenticate")
    fun authenticate(
        @RequestBody request: AuthenticationDtoIn,
    ): ResponseEntity<AuthenticationDtoOut> {
        return ResponseEntity.ok(authenticationService.execute(request))
    }
}
