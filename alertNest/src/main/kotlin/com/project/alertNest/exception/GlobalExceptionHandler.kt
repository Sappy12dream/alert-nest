package com.project.alertNest.exception

import com.project.alertNest.dto.ApiError
import com.project.alertNest.dto.ApiResponse
import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import jakarta.persistence.EntityNotFoundException

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException::class)
    fun handleBadCredentials(ex: BadCredentialsException): ResponseEntity<ApiResponse<Nothing>> {
        val error = ApiError(
                status = HttpStatus.UNAUTHORIZED.value(),
                message = ex.message ?: "Invalid credentials",
                code = "BAD_CREDENTIALS"
        )
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiResponse(error = error))
    }

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleNotFound(ex: EntityNotFoundException): ResponseEntity<ApiResponse<Nothing>> {
        val error = ApiError(
                status = HttpStatus.NOT_FOUND.value(),
                message = ex.message ?: "Resource not found",
                code = "NOT_FOUND"
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse(error = error))
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(ex: IllegalArgumentException): ResponseEntity<ApiResponse<Nothing>> {
        val error = ApiError(
                status = HttpStatus.BAD_REQUEST.value(),
                message = ex.message ?: "Bad request",
                code = "BAD_REQUEST"
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse(error = error))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidation(ex: MethodArgumentNotValidException): ResponseEntity<ApiResponse<Nothing>> {
        val message = ex.bindingResult.fieldErrors.joinToString { it.defaultMessage ?: "Invalid value" }
        val error = ApiError(
                status = HttpStatus.BAD_REQUEST.value(),
                message = message,
                code = "VALIDATION_ERROR"
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse(error = error))
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolation(ex: ConstraintViolationException): ResponseEntity<ApiResponse<Nothing>> {
        val message = ex.constraintViolations.joinToString { it.message }
        val error = ApiError(
                status = HttpStatus.BAD_REQUEST.value(),
                message = message,
                code = "CONSTRAINT_VIOLATION"
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse(error = error))
    }

    @ExceptionHandler(Exception::class)
    fun handleAll(ex: Exception): ResponseEntity<ApiResponse<Nothing>> {
        val error = ApiError(
                status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                message = ex.message ?: "Internal server error",
                code = "INTERNAL_ERROR"
        )
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse(error = error))
    }
}
