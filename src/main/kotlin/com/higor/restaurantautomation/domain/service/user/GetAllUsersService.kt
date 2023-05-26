package com.higor.restaurantautomation.domain.service.user

import com.higor.restaurantautomation.domain.dto.PaginationDto
import com.higor.restaurantautomation.domain.dto.UserDtoOut
import com.higor.restaurantautomation.domain.dto.UsersFilter
import org.springframework.data.domain.Pageable

interface GetAllUsersService {
    fun execute(filter: UsersFilter, pagination: Pageable): PaginationDto<UserDtoOut>
}
