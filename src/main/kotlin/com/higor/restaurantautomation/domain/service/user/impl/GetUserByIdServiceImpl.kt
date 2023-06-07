package com.higor.restaurantautomation.domain.service.user.impl

import com.higor.restaurantautomation.adapters.repository.user.UserRepository
import com.higor.restaurantautomation.domain.model.UserModel
import com.higor.restaurantautomation.domain.service.exception.ApiErrorCodes
import com.higor.restaurantautomation.domain.service.exception.ApiException
import com.higor.restaurantautomation.domain.service.user.GetUserByIdService
import com.higor.restaurantautomation.utils.extensions.from
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class GetUserByIdServiceImpl(
    private val repository: UserRepository,
) : GetUserByIdService {

    @Transactional(readOnly = true)
    @Cacheable(cacheNames = ["userCache"], key = "#id")
    override fun execute(id: UUID): UserModel {
        return repository
            .findById(id)
            .map(UserModel::from)
            .orElseThrow {
                ApiException(ApiErrorCodes.USER_NOT_FOUND)
            }
    }
}
