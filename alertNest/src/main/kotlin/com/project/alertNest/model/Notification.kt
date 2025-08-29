package com.project.alertNest.model

import jakarta.persistence.*
import java.time.Instant
import java.util.*

@Entity
@Table(name = "notifications", schema = "alert_nest")
data class Notification(
        @Id
        @Column(name = "id", updatable = false, nullable = false)
        var id: UUID = UUID.randomUUID(),

        @Column(nullable = false)
        val message: String,

        @Column(nullable = false)
        var read: Boolean = false,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        val user: User,

        @Column(nullable = false, updatable = false)
        val createdAt: Instant = Instant.now()
)
