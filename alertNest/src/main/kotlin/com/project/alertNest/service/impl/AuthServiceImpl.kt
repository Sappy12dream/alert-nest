package com.project.alertNest.service.impl

import com.project.alertNest.model.Account
import com.project.alertNest.model.Role
import com.project.alertNest.model.User
import com.project.alertNest.repository.AccountRepository
import com.project.alertNest.repository.UserRepository
import com.project.alertNest.service.AuthService
import com.project.alertNest.util.JwtUtil
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
        private val userRepository: UserRepository,
        private val accountRepository: AccountRepository,
        private val passwordEncoder: PasswordEncoder,
        private val jwtUtil: JwtUtil
) : AuthService {

    override fun register( email: String, name: String, password: String, accountName: String): User {
        if (userRepository.findByEmail(email) != null) {
            throw IllegalArgumentException("Email already in use")
        }

        // 1️⃣ Create main account (name could be same as username or passed separately)
        val account = Account(
                name = accountName
        )
        accountRepository.save(account)

        val hashedPassword = passwordEncoder.encode(password)
        val user = User(
                email = email,
                name = name,
                user_password = hashedPassword,
                role = Role.ADMIN,
                account = account
        )

        return userRepository.save(user)
    }

    override fun login(email: String, password: String): String {
        val user = userRepository.findByEmail(email)
                ?: throw IllegalArgumentException("Invalid email or password")

        if (!passwordEncoder.matches(password, user.password)) {
            throw IllegalArgumentException("Invalid email or password")
        }

        return jwtUtil.generateToken(user.email) // email as subject
    }
}
