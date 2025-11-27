package com.notification.api.controller.notification;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notification.api.models.request.SendNotificationRequest;
import com.notification.api.services.interfaces.NotificationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor
public class NotificationController {

	private final NotificationService notificationService;
	
	@PostMapping
	public ResponseEntity<String> sendNotification(@Valid @RequestBody SendNotificationRequest notificationRequest)
	{
		notificationService.sendNotification(notificationRequest);
		return ResponseEntity.ok().body("Notification sent successfully");
	}
	
	
}
