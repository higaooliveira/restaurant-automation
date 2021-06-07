package com.higor.restaurantautomation.adapters.repository

import com.higor.restaurantautomation.domain.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ProductRepository : JpaRepository<Product, UUID> {
    fun findAllByCompanyId(companyId: UUID): List<Product>
}
