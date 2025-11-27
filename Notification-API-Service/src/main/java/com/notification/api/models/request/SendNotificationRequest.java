package com.notification.api.models.request;

import java.util.Map;

import com.notification.api.constants.ErrorConstants;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SendNotificationRequest {
	
	
	@NotBlank(message=ErrorConstants.TEMPLATED_ID_IS_REQUIRED)
	private String templateId;
	private Map<String,Object> dynamicVariables;
	private NotificationType notifocationType;

}
