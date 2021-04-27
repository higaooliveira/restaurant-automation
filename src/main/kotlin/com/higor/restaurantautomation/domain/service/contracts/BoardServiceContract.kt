package com.higor.restaurantautomation.domain.service.contracts

import com.higor.restaurantautomation.domain.dto.CreateBoardDto
import com.higor.restaurantautomation.domain.entity.Board

interface BoardServiceContract : Crudable<Board> {
    fun create(createDto: CreateBoardDto): Board
}
