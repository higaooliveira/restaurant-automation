package com.higor.restaurantautomation.controller.user

import com.higor.restaurantautomation.domain.dto.UserDtoIn
import com.higor.restaurantautomation.domain.model.UserModel
import com.higor.restaurantautomation.domain.service.user.CreateUserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping(UserController.BASE_ENDPOINT)
@Validated
class UserController(
    private val createUserService: CreateUserService,
) {

    @PostMapping
    fun list(
        @Valid @RequestBody
        data: UserDtoIn,
    ): ResponseEntity<UUID> {
        println("Estou aqui")
        val userId = createUserService.execute(UserModel.from(data))
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userId)
    }

    @PostMapping
    fun create(
        @Valid @RequestBody
        data: UserDtoIn,
    ): ResponseEntity<UUID> {
        println("Estou aqui")
        val userId = createUserService.execute(UserModel.from(data))
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userId)
    }

    companion object {
        const val BASE_ENDPOINT = "/api/v1/office/user"
    }
}
