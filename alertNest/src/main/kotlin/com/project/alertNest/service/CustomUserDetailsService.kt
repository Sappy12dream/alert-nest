package com.project.alertNest.service

import com.project.alertNest.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
        private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(identifier: String): UserDetails {
        return userRepository.findByEmail(identifier)
                ?: throw UsernameNotFoundException("User not found with email: $identifier")
    }
}
