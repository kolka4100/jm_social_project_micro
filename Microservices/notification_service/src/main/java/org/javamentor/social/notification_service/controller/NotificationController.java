package org.javamentor.social.notification_service.controller;

import lombok.*;
import org.javamentor.social.notification_service.service.LoginServerService;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class NotificationController {

	private final LoginServerService loginServerService;
	private final SimpMessagingTemplate messagingTemplate;

	/**
	 * @Payload для извлечения полезной нагрузки сообщения и, при необходимости,
	 * его преобразовании с помощью Spring MessageConverter.
	 * @param message уведомление для отправки
	 * @param userId идентификатор пользователя
	 * @return сообщение, и отправляет в указанное в @SendTo("URL-адрес") место назначения.
	 */
	@PostMapping("/notification/")  // отображение сообщения, направленного по URL-адресу / сообщению.
//	@SendToUser("/queue/{id}")  // возвращаемое значение должно быть преобразовано в сообщение, если необходимо, и отправлено в указанное место назначения.
	public void sendNotification(@Payload String message,
								   @RequestParam String userId) {
		String email = loginServerService.getEmailById(Long.parseLong(userId));
		messagingTemplate.convertAndSendToUser(
				email, "/secured/user/queue/specific-user", message);
	}

	/**
	 * @MessageExceptionHandler : передать любые исключения, вызванные STOMP,
	 * конечному пользователю в пункте назначения «/queue/errors».
	 * @param exception исключение
	 * @return отправляет String сообщение исключения
	 */
	@MessageExceptionHandler
	@SendToUser("/queue/errors")
	public String handleException(Throwable exception) {
		return exception.getMessage();
	}

//	@MessageMapping("/chat.addUser")
//	@SendTo("/topic/public")
//	public NotificationMessage addUser(@Payload NotificationMessage chatMessage,
//	                           SimpMessageHeaderAccessor headerAccessor) {
//		headerAccessor.getSessionAttributes().put("username",chatMessage.getSender());
//		return chatMessage;
//	}

}
