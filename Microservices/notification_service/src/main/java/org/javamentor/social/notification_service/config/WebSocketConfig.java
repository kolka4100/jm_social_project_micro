package org.javamentor.social.notification_service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.messaging.converter.DefaultContentTypeResolver;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	/**
	 * Регистрирует конечную точку STOMP «/notification».
	 * Эта конечная точка будет использоваться клиентами для подключения к STOMP-серверу.
	 * Здесь также включается резервный SockJS, который будет использоваться,
	 * если WebSocket недоступен.
	 * @param registry StompEndpointRegistry
	 */
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/notification")
				.setAllowedOrigins("*")
				.setHandshakeHandler(
						new DefaultHandshakeHandler() {
							public boolean beforeHandshake(
									ServerHttpRequest request,
									ServerHttpResponse response,
									WebSocketHandler wsHandler,
									Map attributes) throws Exception {

								if (request instanceof ServletServerHttpRequest) {
									ServletServerHttpRequest servletRequest
											= (ServletServerHttpRequest) request;
									HttpSession session = servletRequest
											.getServletRequest().getSession();
									attributes.put("sessionId", session.getId());
								}
								return true;
							}
						})  // получить идентификатор сеанса
				.withSockJS();
	}

	/**
	 * Настройка простого брокера сообщений на основе памяти,
	 * чтобы передавать сообщения обратно клиенту
	 * в места назначения с префиксом «/user/queue/specific-user»
	 * @param config MessageBrokerRegistry
	 */
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/user/queue/specific-user");
		config.setApplicationDestinationPrefixes("/notification");
		config.setUserDestinationPrefix("/user");
	}

	/**
	 * Настраивает конвертер JSON, который используется Spring'ом для преобразования сообщений из/в JSON.
	 * @param messageConverters List
	 * @return false
	 */
	@Override
	public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
		DefaultContentTypeResolver resolver = new DefaultContentTypeResolver();
		resolver.setDefaultMimeType(MimeTypeUtils.APPLICATION_JSON);
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setObjectMapper(new ObjectMapper());
		converter.setContentTypeResolver(resolver);
		messageConverters.add(converter);
		return false;
	}

}
