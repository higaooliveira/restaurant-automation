package com.higor.restaurantautomation.controller.user

import com.higor.restaurantautomation.domain.dto.UserDtoIn
import com.higor.restaurantautomation.domain.dto.UserDtoOut
import com.higor.restaurantautomation.domain.model.UserModel
import com.higor.restaurantautomation.domain.service.user.CreateUserService
import com.higor.restaurantautomation.domain.service.user.GetUserByIdService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import java.util.UUID

@RestController
@RequestMapping(UserController.BASE_ENDPOINT)
@Validated
class UserController(
    private val createUserService: CreateUserService,
    private val getUserByIdService: GetUserByIdService,
) {

    @GetMapping("/{id}")
    fun findById(@PathVariable id: UUID): ResponseEntity<UserDtoOut> {
        val user = getUserByIdService
            .execute(id)
            .run(UserDtoOut::from)

        return ResponseEntity.ok(user)
    }

    @PostMapping
    fun create(
        @Valid @RequestBody
        data: UserDtoIn,
    ): ResponseEntity<Unit> {
        val userId = createUserService.execute(UserModel.from(data))
        return ResponseEntity
            .created(URI.create("$BASE_ENDPOINT/$userId")).build()
    }

    companion object {
        const val BASE_ENDPOINT = "/api/v1/office/user"
    }
}
