package com.higor.restaurantautomation.domain.entity

import javax.persistence.*

@Entity
@Table(name = "board")
data class Board (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        val number: Long,
        var qrCodeLink: String = "",

        @ManyToOne
        @JoinColumn(name = "company_id")
        var company: Company? = null
)