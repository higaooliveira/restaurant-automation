package com.higor.restaurantautomation.domain.entity

import com.fasterxml.jackson.annotation.JsonIgnore
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
    @JsonIgnore
    var company: Company
)
