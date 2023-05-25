package com.higor.restaurantautomation.domain.service.user.impl

import com.higor.restaurantautomation.adapters.repository.user.UserFilters
import com.higor.restaurantautomation.adapters.repository.user.UserRepository
import com.higor.restaurantautomation.domain.dto.PaginationDto
import com.higor.restaurantautomation.domain.dto.UserDtoOut
import com.higor.restaurantautomation.domain.dto.UsersFilter
import com.higor.restaurantautomation.domain.model.UserModel
import com.higor.restaurantautomation.domain.service.user.GetAllUsersService
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class GetAllUsersServiceImpl(
    private val userRepository: UserRepository,
) : GetAllUsersService {
    override fun execute(filter: UsersFilter, pagination: Pageable): PaginationDto<UserDtoOut> {
        val filters = UserFilters
            .name(filter.name)
            .and(UserFilters.email(filter.email))
            .and(UserFilters.phone(filter.phone))
            .and(UserFilters.role(filter.role))

        val allUsers = userRepository.findAll(filters, pagination)

        return PaginationDto
            .Builder<UserDtoOut>()
            .list(
                allUsers
                    .content
                    .map(UserModel::from)
                    .map(UserDtoOut::from),
            )
            .page(allUsers.pageable.pageNumber)
            .totalPages(allUsers.totalPages)
            .totalRecords(allUsers.totalElements.toInt())
            .last(allUsers.isLast)
            .build()
    }
}
