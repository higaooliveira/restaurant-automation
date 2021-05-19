package com.higor.restaurantautomation.adapters.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.Date

@Component
class JWTUtil {

    private val secret = "SECRET"

    private val expiration: Long = 60000

    fun generateToken(username: String): String = Jwts.builder()
        .setSubject(username)
        .setExpiration(Date(System.currentTimeMillis() + expiration))
        .signWith(SignatureAlgorithm.HS512, secret.toByteArray())
        .compact()

    fun isTokenValid(token: String): Boolean {
        val claims = getClaimsToken(token)
        if (claims != null) {
            val username = claims.subject
            val expirationDate = claims.expiration
            val now = Date(System.currentTimeMillis())
            if (username != null && expirationDate != null && now.before(expirationDate)) {
                return true
            }
        }
        return false
    }

    private fun getClaimsToken(token: String): Claims? {
        return try {
            Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJws(token).body
        } catch (e: Exception) {
            null
        }
    }

    fun getId(token: String): String? {
        val claims = getClaimsToken(token)
        return claims?.subject
    }
}
