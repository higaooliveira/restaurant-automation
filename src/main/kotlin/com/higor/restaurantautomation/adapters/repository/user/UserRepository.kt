package com.higor.restaurantautomation.adapters.repository.user

import com.higor.restaurantautomation.adapters.entity.User
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.stereotype.Repository
import java.util.UUID

@EnableJpaRepositories
@Repository
interface UserRepository : JpaSpecificationExecutor<User>, JpaRepository<User, UUID> {

    @Cacheable(cacheNames = ["userDetails"], key = "#email")
    fun findByEmail(email: String): User?

    fun existsByEmail(email: String): Boolean
}
