package com.higor.restaurantautomation.domain.respository

import com.higor.restaurantautomation.domain.entity.Company
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaRepositories
interface CompanyRepository: JpaRepository<Company, Long    > {

    fun findByEmail(email: String): Company?
}
