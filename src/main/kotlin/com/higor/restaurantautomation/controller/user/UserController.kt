package com.higor.restaurantautomation.controller.user

import com.higor.restaurantautomation.adapters.entity.Role
import com.higor.restaurantautomation.domain.dto.PaginationDto
import com.higor.restaurantautomation.domain.dto.UserDtoIn
import com.higor.restaurantautomation.domain.dto.UserDtoOut
import com.higor.restaurantautomation.domain.dto.UsersFilter
import com.higor.restaurantautomation.domain.model.UserModel
import com.higor.restaurantautomation.domain.service.user.CreateUserService
import com.higor.restaurantautomation.domain.service.user.DeleteUserService
import com.higor.restaurantautomation.domain.service.user.GetAllUsersService
import com.higor.restaurantautomation.domain.service.user.GetUserByIdService
import jakarta.validation.Valid
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import java.util.UUID

@RestController
@RequestMapping(UserController.BASE_ENDPOINT)
@Validated
class UserController(
    private val createUserService: CreateUserService,
    private val getUserByIdService: GetUserByIdService,
    private val deleteUserService: DeleteUserService,
    private val getAllUsersService: GetAllUsersService,
) {

    @GetMapping
    fun list(
        @RequestParam("email") email: String?,
        @RequestParam("role") role: Role?,
        @RequestParam("name") name: String?,
        @RequestParam("phone") phone: String?,
        pagination: Pageable,
    ): ResponseEntity<PaginationDto<UserDtoOut>> {
        val filter = UsersFilter(
            email = email,
            role = role,
            name = name,
            phone = phone,
        )
        val pagedUsers = getAllUsersService.execute(filter, pagination)

        return ResponseEntity.ok(pagedUsers)
    }

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

    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable id: UUID,
    ): ResponseEntity<Unit> {
        deleteUserService.execute(id)
        return ResponseEntity.noContent().build()
    }

    companion object {
        const val BASE_ENDPOINT = "/api/v1/office/user"
    }
}
