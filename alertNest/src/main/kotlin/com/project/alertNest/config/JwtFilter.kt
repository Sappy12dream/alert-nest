package com.project.alertNest.config

import com.project.alertNest.service.CustomUserDetailsService
import com.project.alertNest.util.JwtUtil
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtFilter(private val jwtUtil: JwtUtil,
                private val userDetailsService: CustomUserDetailsService
) : OncePerRequestFilter(){
    override fun doFilterInternal(
            request: HttpServletRequest,
            response: HttpServletResponse,
            filterChain: FilterChain
    ) {
        val token = getJwtFromCookie(request)
        if (token != null && jwtUtil.validateToken(token)) {
            val username = jwtUtil.getUsernameFromToken(token)
            val userDetails: UserDetails = userDetailsService.loadUserByUsername(username)

            val authToken = UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.authorities
            )
            SecurityContextHolder.getContext().authentication = authToken
        }

        filterChain.doFilter(request, response)
    }
    private fun getJwtFromCookie(request: HttpServletRequest): String? {
        val cookies = request.cookies ?: return null
        return cookies.firstOrNull { it.name == "AUTH_TOKEN" }?.value
    }
}