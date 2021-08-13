package org.javamentor.social.notification_service.model;


import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import javax.persistence.*;

@Entity
@Table(name = "notification")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Log4j2
public class UserNotification implements Message<String> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	private String payload;
	private MessageHeaders headers = null;  // TODO
	private String to;

	@Override
	public MessageHeaders getHeaders() {  // TODO
		return null;
	}  // TODO
}
