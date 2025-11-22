package com.notification.api.models.request;

import java.util.Map;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TemplateRequest {
	
	
	@NotBlank(message="Name field is required")
	private String name;
	private Map<String,String> templateVariables;
	@NotBlank(message="Message template field is required")
	private String  messageTemplate;
	

}
