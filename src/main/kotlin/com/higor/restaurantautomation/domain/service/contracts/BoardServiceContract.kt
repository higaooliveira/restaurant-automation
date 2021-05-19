package com.higor.restaurantautomation.domain.service.contracts

import com.higor.restaurantautomation.domain.dto.CreateBoardDto
import com.higor.restaurantautomation.domain.entity.Board
import java.util.UUID

interface BoardServiceContract : Crudable<Board> {
    fun create(createDto: CreateBoardDto): Board
    fun getAll(companyId: UUID): List<Board>
}
