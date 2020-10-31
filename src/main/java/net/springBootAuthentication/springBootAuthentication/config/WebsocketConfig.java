package net.springBootAuthentication.springBootAuthentication.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import net.springBootAuthentication.springBootAuthentication.services.ChatWebsocketHandler;

@Configuration
@EnableWebSocket
public class WebsocketConfig implements WebSocketConfigurer {

    private static String chatEndpoint = "api/*";
    

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
            registry.addHandler(gWebSocketHandler(), chatEndpoint).setAllowedOrigins("*");
            System.out.println(chatEndpoint);
    }

    @Bean
    public WebSocketHandler gWebSocketHandler() {
        return new ChatWebsocketHandler();
    }
}
