package org.javamentor.social.notification_service.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("login-service")
public interface LoginServerService {
	String getEmailById(long id);
}
