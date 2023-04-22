package com.higor.restaurantautomation.adapters.entity

import com.higor.restaurantautomation.domain.dto.BoardResponse
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "board")
data class Board(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID,

    val number: Long,
    var qrCodeLink: String = "",

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    var company: Company,
) {
    fun toBoardResponse() = BoardResponse(
        id = this.id,
        number = this.number,
        qrCodeLink = this.qrCodeLink,
    )
}
