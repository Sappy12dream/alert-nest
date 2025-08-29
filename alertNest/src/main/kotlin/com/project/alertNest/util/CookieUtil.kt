package com.project.alertNest.util

import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import jakarta.servlet.http.HttpServletRequest

object CookieUtil {

    const val AUTH_COOKIE_NAME = "AUTH_TOKEN"

    fun createCookie(token: String, maxAge: Int = 24 * 60 * 60, secure: Boolean = true): Cookie {
        return Cookie(AUTH_COOKIE_NAME, token).apply {
            isHttpOnly = true
            path = "/"
            this.secure = secure
            this.maxAge = maxAge
        }
    }

    fun addCookie(response: HttpServletResponse, token: String) {
        response.addCookie(createCookie(token))
    }

    fun deleteCookie(response: HttpServletResponse) {
        val cookie = Cookie(AUTH_COOKIE_NAME, null).apply {
            isHttpOnly = true
            path = "/"
            maxAge = 0
        }
        response.addCookie(cookie)
    }

    fun getCookie(request: HttpServletRequest): String? {
        val cookies = request.cookies ?: return null
        return cookies.firstOrNull { it.name == AUTH_COOKIE_NAME }?.value
    }
}
