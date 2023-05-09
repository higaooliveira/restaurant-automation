package com.higor.restaurantautomation.domain.service.user.impl

import com.higor.restaurantautomation.adapters.repository.UserRepository
import com.higor.restaurantautomation.domain.model.UserModel
import com.higor.restaurantautomation.domain.service.exception.ApiErrorCodes
import com.higor.restaurantautomation.domain.service.exception.ApiException
import com.higor.restaurantautomation.domain.service.user.GetUserByIdService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class GetUserByIdServiceImpl(
    private val repository: UserRepository,
) : GetUserByIdService {

    @Transactional(readOnly = true)
    override fun execute(id: UUID): UserModel {
        return repository
            .findById(id)
            .map(UserModel::from)
            .orElseThrow {
                ApiException(ApiErrorCodes.USER_NOT_FOUND)
            }
    }
}