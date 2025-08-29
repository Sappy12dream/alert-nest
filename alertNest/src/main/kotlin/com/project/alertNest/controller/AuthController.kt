package com.project.alertNest.controller

import com.project.alertNest.dto.*
import com.project.alertNest.service.AuthService
import com.project.alertNest.util.CookieUtil
import jakarta.servlet.http.HttpServletResponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(private val authService: AuthService) {

    @PostMapping("/signup")
    fun signup(
            @Valid @RequestBody request: SignUpRequest,
            response: HttpServletResponse
    ): ResponseEntity<ApiResponse<String>> {
        val user = authService.register(
                email = request.email,
                name = request.name,
                password = request.password,
                accountName = request.accountName
        )
        val token = authService.login(request.email, request.password) // auto-login
        CookieUtil.addCookie(response, token)
        return ResponseEntity.ok(ApiResponse(data = "User registered successfully"))
    }

    @PostMapping("/signin")
    fun signin(
            @Valid @RequestBody request: SignInRequest,
            response: HttpServletResponse
    ): ResponseEntity<ApiResponse<String>> {
        val token = authService.login(request.email, request.password)
        CookieUtil.addCookie(response, token)
        return ResponseEntity.ok(ApiResponse(data = "Login successful"))
    }

    @PostMapping("/logout")
    fun logout(response: HttpServletResponse): ResponseEntity<ApiResponse<String>> {
        CookieUtil.deleteCookie(response)
        return ResponseEntity.ok(ApiResponse(data = "Logged out successfully"))
    }


}
