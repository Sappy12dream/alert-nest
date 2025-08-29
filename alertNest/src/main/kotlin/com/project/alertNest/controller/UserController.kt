package com.project.alertNest.controller

import com.project.alertNest.dto.ApiError
import com.project.alertNest.dto.ApiResponse
import com.project.alertNest.dto.UserDto
import com.project.alertNest.model.User
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {

    @GetMapping("/api/v1/me")
    fun getCurrentUser(@AuthenticationPrincipal user: User?): ResponseEntity<ApiResponse<Any>> {
        return if (user != null) {
            val dto = UserDto(
                    id = user.id,
                    name = user.name,
                    email = user.email,
                    role = user.role
            )
            ResponseEntity.ok(ApiResponse(data = dto))
        } else {
            val error = ApiError(
                    status = 401,
                    message = "Unauthorized",
                    code = "UNAUTHORIZED"
            )
            ResponseEntity.status(401).body(ApiResponse(error = error))
        }
    }
}
