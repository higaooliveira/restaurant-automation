package com.higor.restaurantautomation.configuration.security

import com.higor.restaurantautomation.domain.model.user.UserModel
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.Key
import java.util.Date

@Component
class JWTUtil {

    @Value("\${jwt.secret}")
    private val secretKey: String = "SECRET"

    @Value("\${jwt.expiration-time}")
    private val expiration: Long = 60

    fun generateToken(userDetails: UserDetailsImpl): String {
        return Jwts
            .builder()
            .setSubject(userDetails.getId().toString())
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + expiration))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact()
    }

    fun isTokenValid(token: String, user: UserModel): Boolean {
        val claims = extractAllClaims(token) ?: return false

        val userId = claims.subject

        return (userId == user.id.toString()) && !isTokenExpired(token)
    }

    fun getId(token: String): String? {
        val claims = extractAllClaims(token)
        return claims?.subject
    }

    private fun getSignInKey(): Key {
        val keyBytes = secretKey.toByteArray()

        return Keys.hmacShaKeyFor(keyBytes)
    }

    private fun isTokenExpired(token: String): Boolean {
        val claims = extractAllClaims(token) ?: return true
        return claims.expiration.before(Date(System.currentTimeMillis()))
    }

    private fun extractAllClaims(token: String): Claims? {
        return Jwts
            .parserBuilder()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(token)
            .body
    }
}
