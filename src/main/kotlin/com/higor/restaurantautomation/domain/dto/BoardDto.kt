package com.higor.restaurantautomation.domain.dto

import java.util.UUID

data class CreateBoard(
    val number: Long,
    var companyId: UUID? = null
)

data class BoardResponse(
    val id: UUID,
    val number: Long,
    val qrCodeLink: String
)

data class PagedBoardsResponse(
    val boards: List<BoardResponse>,
    val page: Int,
    val size: Int,
    val totalPages: Int,
    val lastPage: Boolean
)
