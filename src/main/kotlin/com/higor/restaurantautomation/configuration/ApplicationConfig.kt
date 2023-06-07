package com.higor.restaurantautomation.configuration

import com.higor.restaurantautomation.adapters.repository.user.UserRepository
import com.higor.restaurantautomation.configuration.security.JWTUtil
import com.higor.restaurantautomation.configuration.security.UserDetailsImpl
import com.higor.restaurantautomation.domain.model.UserModel
import com.higor.restaurantautomation.utils.extensions.from
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import kotlin.jvm.Throws

@Configuration
class ApplicationConfig(
    private val userRepository: UserRepository,
) {
    @Bean
    fun authenticationProvider(): AuthenticationProvider {
        val authenticationProvider = DaoAuthenticationProvider()
        authenticationProvider.setUserDetailsService(userDetailsService())
        authenticationProvider.setPasswordEncoder(passwordEncoder())
        return authenticationProvider
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun userDetailsService(): UserDetailsService {
        return UserDetailsService { username ->
            val user = userRepository
                .findByEmail(username) ?: throw UsernameNotFoundException("User Not Found")
            UserDetailsImpl(UserModel.from(user))
        }
    }

    @Bean
    fun jwtUtil(): JWTUtil {
        return JWTUtil()
    }

    @Bean
    @Throws(Exception::class)
    fun authenticationManager(config: AuthenticationConfiguration): AuthenticationManager {
        return config.authenticationManager
    }
}
