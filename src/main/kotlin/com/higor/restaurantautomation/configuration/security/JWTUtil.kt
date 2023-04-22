package com.higor.restaurantautomation.configuration.security

import com.higor.restaurantautomation.adapters.entity.Company
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.security.Key
import java.util.Date
import javax.crypto.SecretKey

@Component
class JWTUtil {

    @Value("\${jwt.secret}")
    private val secretKey: String = "SECRET"

    @Value("\${jwt.expiration-time}")
    private val expiration: Long = 60

    fun generateToken(userDetails: Company): String  {
        return Jwts
            .builder()
            .setSubject(userDetails.id.toString())
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + expiration))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact()
    }

    fun isTokenValid(token: String, company: Company): Boolean {
        val claims = extractAllClaims(token) ?: return false

        val companyId = claims.subject

        return (companyId == company.id.toString()) && !isTokenExpired(token)
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
            .setSigningKey(getSignInKey()   )
            .build()
            .parseClaimsJws(token)
            .body

    }
}
