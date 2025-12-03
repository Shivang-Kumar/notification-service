package com.notification.processor.services.interfaces;

import org.jspecify.annotations.Nullable;

import com.notification.processor.models.request.CreateTemplateRequest;
import com.notification.processor.models.request.TemplateFilterRequest;
import com.notification.processor.models.request.UpdateTemplateRequest;
import com.notification.processor.models.response.FilterTemplateResponse;
import com.notification.processor.models.response.TemplateResponse;

import jakarta.validation.Valid;

public interface TemplateService {

	
	TemplateResponse createTemplate(CreateTemplateRequest request);

	
	FilterTemplateResponse filterTemplate(TemplateFilterRequest request);


	
	TemplateResponse updateTemplate(String id,UpdateTemplateRequest request);


	
	void deleteTemplate(String id);

}
