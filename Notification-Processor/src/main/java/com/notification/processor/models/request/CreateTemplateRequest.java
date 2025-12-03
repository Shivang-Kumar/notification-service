package com.notification.processor.models.request;

import java.util.Map;

import com.notification.processor.constants.ErrorConstants;
import com.notification.processor.utils.CommonUtils;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateTemplateRequest {
	
	
	@NotBlank(message="Name field is required")
	@Size(max=10000, message=ErrorConstants.TEMPLATE_NAME_ERROR)
	private String name;
	private Map<String,String> templateVariables;
	
	
	@Size(max=10000, message=ErrorConstants.TEMPLATE_MESSAGE_ERROR)
	@NotBlank(message="Message template field is required")
	private String  messageTemplate;
	
	
	@AssertTrue(message=ErrorConstants.TEMPLATE_VARIABLE_ERROR)
	public boolean validateTemplateVariable() {
		return CommonUtils.isEmpty(templateVariables) && (templateVariables.size()<=20);
	}
  
}
