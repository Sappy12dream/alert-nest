package com.project.alertNest.controller

import com.project.alertNest.model.Notification
import com.project.alertNest.model.User
import com.project.alertNest.service.NotificationService
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/notifications")
class NotificationController(
        private val notificationService: NotificationService,
        private val messagingTemplate: SimpMessagingTemplate
) {

    // Fetch all unread notifications for the logged-in user
    @GetMapping
    fun getUnreadNotifications(@AuthenticationPrincipal user: User): List<Notification> {
        return notificationService.getUnreadNotifications(user)
    }

    // Mark a notification as read
    @PostMapping("/{id}/read")
    fun markAsRead(@PathVariable id: UUID) {
        notificationService.markAsRead(id)
    }

    // Send a notification to a user in real-time
    fun sendNotification(user: User, message: String) {
        val notification = notificationService.createNotification(user, message)
        // Send via WebSocket to /user/{email}/queue/notifications
        messagingTemplate.convertAndSendToUser(
                user.email,
                "/queue/notifications",
                notification
        )
    }
}
