package com.higor.restaurantautomation.adapters.repository

import com.higor.restaurantautomation.adapters.entity.Board
import com.higor.restaurantautomation.adapters.entity.Company
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface BoardRepository : JpaRepository<Board, UUID> {
    fun findByNumberAndCompany(number: Long, company: Company): Board?

    fun findAllByCompanyId(companyId: UUID, pageable: Pageable): Page<Board>
}
