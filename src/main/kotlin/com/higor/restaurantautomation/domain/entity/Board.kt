package com.higor.restaurantautomation.domain.entity

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "board")
data class Board(
    @Id
    val id: UUID = UUID.randomUUID(),

    val number: Long,
    var qrCodeLink: String = "",

    @ManyToOne
    @JoinColumn(name = "company_id")
    var company: Company? = null
)
