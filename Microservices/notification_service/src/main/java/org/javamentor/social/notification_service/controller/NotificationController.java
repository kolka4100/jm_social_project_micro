package org.javamentor.social.notification_service.controller;

import org.javamentor.social.notification_service.model.NotificationMessage;
import org.javamentor.social.notification_service.service.LoginServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

	private final LoginServerService loginServerService;

	@Autowired
	public NotificationController(LoginServerService loginServerService) {
		this.loginServerService = loginServerService;
	}

	@MessageMapping("/notification/{id}")
	@SendTo("/topic/{id}")
	public NotificationMessage sendNotification(@Payload NotificationMessage chatMessage, @RequestBody Long userId) {
		String email = loginServerService.getEmailById(userId);
		return chatMessage;  // TODO
	}

//	@MessageMapping("/chat.addUser")
//	@SendTo("/topic/public")
//	public NotificationMessage addUser(@Payload NotificationMessage chatMessage,
//	                           SimpMessageHeaderAccessor headerAccessor) {
//		headerAccessor.getSessionAttributes().put("username",chatMessage.getSender());
//		return chatMessage;
//	}

}
