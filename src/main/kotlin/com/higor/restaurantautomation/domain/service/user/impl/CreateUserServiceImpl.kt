package com.higor.restaurantautomation.domain.service.user.impl

import com.higor.restaurantautomation.adapters.repository.user.UserRepository
import com.higor.restaurantautomation.domain.model.UserModel
import com.higor.restaurantautomation.domain.service.company.GetCompanyByIdService
import com.higor.restaurantautomation.domain.service.exception.ApiErrorCodes
import com.higor.restaurantautomation.domain.service.exception.ApiException
import com.higor.restaurantautomation.domain.service.user.CreateUserService
import com.higor.restaurantautomation.domain.service.user.UserExistsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class CreateUserServiceImpl(
    private val repository: UserRepository,
    private val userExistsService: UserExistsService,
    private val getCompanyByIdService: GetCompanyByIdService,
) : CreateUserService {

    @Transactional
    override fun execute(data: UserModel): UUID {
        if (userExistsService.execute(data.email)) {
            throw ApiException(ApiErrorCodes.USER_EXISTS)
        }

        val company = getCompanyByIdService.execute(data.companyId)
        val user = repository.save(data.toEntity(company))

        return user.id!!
    }
}
