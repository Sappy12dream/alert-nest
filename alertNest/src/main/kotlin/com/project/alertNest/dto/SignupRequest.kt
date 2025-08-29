package com.project.alertNest.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class SignUpRequest(
        @field:NotBlank(message = "Name is required")
        val name: String,

        @field:NotBlank(message = "Email is required")
        @field:Email(message = "Email should be valid")
        val email: String,

        @field:NotBlank(message = "Password is required")
        @field:Size(min = 6, message = "Password must be at least 6 characters")
        val password: String,

        @field:NotBlank(message = "Account name is required")
        val accountName: String
)
