package com.higor.restaurantautomation.adapters.repository.product

import com.higor.restaurantautomation.adapters.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ProductRepository : JpaRepository<Product, UUID>, JpaSpecificationExecutor<Product>
