package com.higor.restaurantautomation.domain.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.higor.restaurantautomation.utils.PasswordHelper
import javax.persistence.*

@Entity
@Table(name = "company")
data class Company(
        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        val id: Long? = null,

        val name: String,
        val email: String,

        @JsonIgnore
        var password: String,
        val phone: String,
        val document: String,

        @OneToMany(mappedBy = "company")
        @JsonIgnore
        var boards: List<Board> = ArrayList()
){
        fun encodePassword(){
                this.password = PasswordHelper.encode(this.password)
        }

}