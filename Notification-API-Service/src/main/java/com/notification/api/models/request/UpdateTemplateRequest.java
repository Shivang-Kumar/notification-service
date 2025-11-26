package com.notification.api.models.request;

import java.util.Map;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateTemplateRequest {
	
	
	
	private String name;
	private Map<String,String> templateVariables;
	private String  messageTemplate;
	

}
