package com.example.scorecard.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import com.example.scorecard.common.util.Constants;


@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry brokerRegistry) {
		// This is the parent prefix for the stomo msg broker. 
		brokerRegistry.enableSimpleBroker(Constants.WS_BROKER_PARENT_PREFIX);
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry stompRegistry) {
		stompRegistry.addEndpoint(Constants.WS_URL_END_POINT).withSockJS().setHeartbeatTime(5000);
	}
	
}
