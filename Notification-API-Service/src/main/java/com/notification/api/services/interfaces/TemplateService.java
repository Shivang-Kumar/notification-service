package com.notification.api.services.interfaces;

import org.jspecify.annotations.Nullable;

import com.notification.api.models.request.CreateTemplateRequest;
import com.notification.api.models.request.TemplateFilterRequest;
import com.notification.api.models.response.TemplateResponse;

public interface TemplateService {

	
	TemplateResponse createTemplate(CreateTemplateRequest request);

	
	Object filterTemplate(TemplateFilterRequest request);

}
