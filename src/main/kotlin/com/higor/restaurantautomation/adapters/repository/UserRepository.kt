package com.higor.restaurantautomation.adapters.repository

import com.higor.restaurantautomation.adapters.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import java.util.UUID

@EnableJpaRepositories
interface UserRepository : JpaRepository<User, UUID> {
    fun findByEmail(email: String): User?

    fun existsByEmail(email: String): Boolean
}
