package com.project.alertNest.service.impl

import com.project.alertNest.model.Notification
import com.project.alertNest.model.User
import com.project.alertNest.repository.NotificationRepository
import com.project.alertNest.service.NotificationService
import org.springframework.stereotype.Service
import java.util.*

@Service
class NotificationServiceImpl(
        private val notificationRepository: NotificationRepository
) : NotificationService {

    override fun createNotification(user: User, message: String): Notification {
        val notification = Notification(
                user = user,
                message = message
        )
        return notificationRepository.save(notification)
    }

    override fun getUnreadNotifications(user: User): List<Notification> =
            notificationRepository.findByUserAndReadFalse(user)

    override fun markAsRead(notificationId: UUID) {
        val notification = notificationRepository.findById(notificationId)
                .orElseThrow { IllegalArgumentException("Notification not found") }
        notification.read = true
        notificationRepository.save(notification)
    }
}
