package com.higor.restaurantautomation.domain.entity

import com.higor.restaurantautomation.domain.dto.BoardResponse
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

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
    var company: Company
) {
    fun toBoardResponse() = BoardResponse(
        id = this.id,
        number = this.number,
        qrCodeLink = this.qrCodeLink
    )
}
