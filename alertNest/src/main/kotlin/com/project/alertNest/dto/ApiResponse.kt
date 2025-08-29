package com.project.alertNest.dto

data class ApiResponse<T>(
        val data: T? = null,
        val error: ApiError? = null
)