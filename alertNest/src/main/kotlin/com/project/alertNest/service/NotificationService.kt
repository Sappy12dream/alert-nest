package com.project.alertNest.service

import com.project.alertNest.model.Notification
import com.project.alertNest.model.User
import java.util.*

interface NotificationService {
    fun createNotification(user: User, message: String): Notification
    fun getUnreadNotifications(user: User): List<Notification>
    fun markAsRead(notificationId: UUID)
}
