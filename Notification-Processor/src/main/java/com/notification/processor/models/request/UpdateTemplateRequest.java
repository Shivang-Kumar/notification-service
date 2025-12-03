package com.notification.processor.models.request;

import java.util.Map;

import org.springframework.http.HttpStatus;

import com.notification.processor.constants.ErrorConstants;
import com.notification.processor.exception.ValidationException;
import com.notification.processor.utils.CommonUtils;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateTemplateRequest {
	
	
	
	private String name;
	private Map<String,String> templateVariables;
	private String  messageTemplate;
	
	
	

	@AssertTrue
	public boolean validateTemplateVariable() {
		if(CommonUtils.isEmpty(templateVariables) && (templateVariables.size()>=20))
		throw new  ValidationException(ErrorConstants.TEMPLATE_VARIABLE_ERROR);
		
		if(CommonUtils.isNotEmpty(name) && name.trim().length()>20)
		throw new  ValidationException(ErrorConstants.TEMPLATE_NAME_ERROR);
		
		if(CommonUtils.isNotEmpty(messageTemplate) && messageTemplate.trim().length()>10000)
			throw new  ValidationException(ErrorConstants.TEMPLATE_MESSAGE_ERROR);
			
		
		return true;

	}





}
