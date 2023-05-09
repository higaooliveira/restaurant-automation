package com.higor.restaurantautomation.domain.service.user.impl

import com.higor.restaurantautomation.adapters.repository.UserRepository
import com.higor.restaurantautomation.domain.service.exception.ApiErrorCodes
import com.higor.restaurantautomation.domain.service.exception.ApiException
import com.higor.restaurantautomation.domain.service.user.DeleteUserService
import org.springframework.cache.annotation.CacheEvict
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class DeleteUserServiceImpl(
    private val repository: UserRepository,
) : DeleteUserService {

    @Transactional
    @CacheEvict(cacheNames = ["userCache"], key = "#id")
    override fun execute(id: UUID) {
        runCatching {
            repository.deleteById(id)
        }.getOrElse {
            throw ApiException(errorCode = ApiErrorCodes.USER_NOT_FOUND, cause = it)
        }
    }
}
