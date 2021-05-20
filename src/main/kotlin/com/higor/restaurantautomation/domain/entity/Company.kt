package com.higor.restaurantautomation.domain.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.higor.restaurantautomation.utils.PasswordHelper
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "company")
data class Company(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    val id: UUID,

    val name: String,
    val email: String,
    @JsonIgnore
    var password: String,
    val phone: String,
    val document: String,
) {
    fun encodePassword() {
        this.password = PasswordHelper.encode(this.password)
    }
}
