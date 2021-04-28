package com.higor.restaurantautomation.domain.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.higor.restaurantautomation.utils.PasswordHelper
import java.util.UUID
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "company")
data class Company(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.randomUUID(),

    val name: String,
    val email: String,

    @JsonIgnore
    var password: String,
    val phone: String,
    val document: String,

    @OneToMany(
        mappedBy = "company",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    @JsonIgnore
    var boards: Set<Board> = HashSet()
) {
    fun encodePassword() {
        this.password = PasswordHelper.encode(this.password)
    }
}
