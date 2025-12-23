package com.notification.api.controller.notification;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.common.sdk.models.interfaces.GenericApiResponse;
import com.common.sdk.services.ResponseHandler;
import com.notification.api.models.request.SendNotificationRequest;
import com.notification.api.services.interfaces.NotificationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor
public class NotificationController {

	private final NotificationService notificationService;
	private final ResponseHandler responseHandler;
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public GenericApiResponse<String> sendNotification(@Valid @RequestBody SendNotificationRequest notificationRequest)
	{
		notificationService.sendNotification(notificationRequest);
		return responseHandler.ok("Notification sent successfully");
	}
	
	
}
