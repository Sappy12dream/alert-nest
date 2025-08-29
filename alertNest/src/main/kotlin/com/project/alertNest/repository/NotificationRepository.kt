package com.project.alertNest.repository

import com.project.alertNest.model.Notification
import com.project.alertNest.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface NotificationRepository : JpaRepository<Notification, UUID> {
    fun findByUserAndReadFalse(user: User): List<Notification>
}
