package com.project.alertNest.model

import jakarta.persistence.*
import java.time.Instant
import java.util.*

@Entity
@Table(name = "accounts", schema = "alert_nest")
data class Account(
        @Id
        @Column(name = "id", updatable = false, nullable = false)
        var id: UUID = UUID.randomUUID(),

        @Column(unique = true, nullable = false)
        var name: String,

        @OneToMany(mappedBy = "account", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        var users: MutableList<User> = mutableListOf(),

        @Column(nullable = false, updatable = false)
        var createdAt: Instant = Instant.now(),

        @Column(nullable = false)
        var updatedAt: Instant = Instant.now()
) {

    @PrePersist
    fun prePersist() {
        val now = Instant.now()
        createdAt = now
        updatedAt = now
    }

    @PreUpdate
    fun preUpdate() {
        updatedAt = Instant.now()
    }
}
