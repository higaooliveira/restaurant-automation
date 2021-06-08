package com.higor.restaurantautomation.domain.service.contracts

import com.higor.restaurantautomation.domain.dto.BoardResponse
import com.higor.restaurantautomation.domain.dto.CreateBoard
import com.higor.restaurantautomation.domain.dto.PagedBoardsResponse
import com.higor.restaurantautomation.domain.entity.Board
import org.springframework.data.domain.Pageable
import java.util.UUID

interface BoardServiceContract : Crudable<Board> {
    fun create(createDto: CreateBoard): BoardResponse
    fun getAll(companyId: UUID, pageable: Pageable): PagedBoardsResponse
}
