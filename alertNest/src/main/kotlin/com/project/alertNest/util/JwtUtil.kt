package com.project.alertNest.util

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.Jwts
import java.util.Date

@Component
class JwtUtil(
        @Value("\${jwt.secret}") private val secret: String,
        @Value("\${jwt.expiration-ms}") private val expirationMs: Long,
) {
    private val  key = Keys.hmacShaKeyFor(secret.toByteArray());

    fun generateToken(username: String): String{
        val now = Date();
        val expiry = Date(now.time + expirationMs);
        return  Jwts.builder()
                .subject(username)        // modern builder style
                .issuedAt(Date())
                .expiration(expiry)
                .signWith(key)
                .compact()
    }
    fun getUsernameFromToken(token: String): String =
            Jwts.parser().verifyWith(key).build()
                    .parseSignedClaims(token).payload.subject

    fun validateToken(token: String): Boolean =
            try {
                getUsernameFromToken(token)
                true
            } catch (ex: Exception) {
                false
            }

}