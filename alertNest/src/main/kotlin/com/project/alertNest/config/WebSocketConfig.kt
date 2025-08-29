package com.project.alertNest.config

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig : WebSocketMessageBrokerConfigurer {

    override fun configureMessageBroker(config: MessageBrokerRegistry) {
        // Enable simple in-memory broker for destinations starting with /topic or /queue
        config.enableSimpleBroker("/topic", "/queue")
        // Set application destination prefix for sending messages from client to server
        config.setApplicationDestinationPrefixes("/app")
        // Set prefix for user-specific messages
        config.setUserDestinationPrefix("/user")
    }

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        // WebSocket endpoint clients connect to
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*")  // allow all origins for dev, lock down in production
                .withSockJS()                    // fallback for browsers without WebSocket
    }
}
