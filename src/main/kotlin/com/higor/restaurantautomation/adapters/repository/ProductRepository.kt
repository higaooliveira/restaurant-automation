package com.higor.restaurantautomation.adapters.repository

import com.higor.restaurantautomation.adapters.entity.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ProductRepository : JpaRepository<Product, UUID> {
    fun findAllByCompanyId(companyId: UUID, pageable: Pageable): Page<Product>
}
