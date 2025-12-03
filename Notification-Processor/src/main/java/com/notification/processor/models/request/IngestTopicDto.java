package com.notification.processor.models.request;

import java.util.Map;

import lombok.Data;

@Data
public class IngestTopicDto {
	
	
	private String requestId;
	private String tenantId;
	private long receivedAt;
	private String templateId;
	private Map<String,Object> dynamicVariables;
	private NotificationType notificationType;
	
	

}
