package com.higor.restaurantautomation.adapters.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null,

    @Column(name = "name")
    var name: String,

    @Column(name = "email", unique = true)
    var email: String,

    @Column(name = "password")
    var password: String,

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    var role: Role,

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = [CascadeType.REFRESH])
    @JoinColumn(name = "company_id", nullable = false)
    var company: Company,

    @Column(name = "created_at")
    var createdAt: Instant = Instant.now(),

    @Column(name = "updated_at")
    var updatedAt: Instant? = null,
)
