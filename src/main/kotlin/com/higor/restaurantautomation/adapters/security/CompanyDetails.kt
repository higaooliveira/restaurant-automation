package com.higor.restaurantautomation.adapters.security

import com.higor.restaurantautomation.domain.entity.Company
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.UUID

class CompanyDetails(private val company: Company) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = mutableListOf()
    override fun isEnabled(): Boolean = true
    override fun getUsername(): String = company.email
    override fun isCredentialsNonExpired(): Boolean = true
    override fun getPassword(): String = company.password
    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    fun getId(): UUID = company.id
}
