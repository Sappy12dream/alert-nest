package com.project.alertNest.dto

import com.project.alertNest.model.Role
import java.util.*

data class UserDto(
        val id: UUID,
        val name: String,
        val email: String,
        val role: Role
)
