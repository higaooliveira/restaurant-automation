package com.higor.restaurantautomation.configuration.security

import com.higor.restaurantautomation.domain.model.user.UserModel
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.UUID

class UserDetailsImpl(private val user: UserModel) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority(user.role.name))
    }
    override fun isEnabled(): Boolean {
        return true
    }
    override fun getUsername(): String {
        return user.email
    }
    override fun isCredentialsNonExpired(): Boolean {
        return true
    }
    override fun getPassword(): String {
        return user.password
    }
    override fun isAccountNonExpired(): Boolean {
        return true
    }
    override fun isAccountNonLocked(): Boolean {
        return true
    }
    fun getId(): UUID? {
        return user.id
    }
}
