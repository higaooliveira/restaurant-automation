package com.higor.restaurantautomation.adapters.repository

import com.higor.restaurantautomation.domain.entity.Board
import com.higor.restaurantautomation.domain.entity.Company
import org.springframework.data.jpa.repository.JpaRepository

interface BoardRepository : JpaRepository<Board, Long>{
    fun findByNumberAndCompany(number: Long, company: Company) : Board?
}