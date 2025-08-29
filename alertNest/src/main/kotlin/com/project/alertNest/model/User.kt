package com.project.alertNest.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.Instant
import java.util.*

@Entity
@Table(name = "users", schema = "alert_nest")
data class User(
        @Id
        @Column(name = "id", updatable = false, nullable = false)
        var id: UUID = UUID.randomUUID(),

        @Column(unique = true, nullable = false)
        var email: String,

        @Column(nullable = false)
        var name: String,

        @Column(nullable = false)
        var user_password: String,

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        var role: Role = Role.ADMIN,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "account_id")
        var account: Account? = null,

        @Column(nullable = false, updatable = false)
        var createdAt: Instant = Instant.now(),

        @Column(nullable = false)
        var updatedAt: Instant = Instant.now()
) : UserDetails {

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

    // ---- UserDetails implementation ----
    override fun getAuthorities(): Collection<GrantedAuthority> =
            listOf(SimpleGrantedAuthority("ROLE_${role.name}"))

    override fun getPassword(): String = user_password

    // You can decide whether to use `username` or `email` as the login ID
    override fun getUsername(): String = email

    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = true
}
