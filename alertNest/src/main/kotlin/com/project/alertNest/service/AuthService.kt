package com.project.alertNest.service

import com.project.alertNest.model.User

interface AuthService {
    fun register( email: String, name: String, password: String, accountName: String): User
    fun login(email: String, password: String): String
}