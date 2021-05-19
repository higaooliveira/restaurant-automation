package com.higor.restaurantautomation.adapters.repository

import com.higor.restaurantautomation.domain.entity.Company
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import java.util.UUID

@EnableJpaRepositories
interface CompanyRepository : JpaRepository<Company, UUID> {

    fun findByEmail(email: String): Company?
}
