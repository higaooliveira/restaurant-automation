package com.higor.restaurantautomation.adapters.repository

import com.higor.restaurantautomation.domain.entity.Board
import com.higor.restaurantautomation.domain.entity.Company
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface BoardRepository : JpaRepository<Board, UUID> {
    fun findByNumberAndCompany(number: Long, company: Company): Board?

    fun findAllByCompanyId(companyId: UUID): List<Board>
}
