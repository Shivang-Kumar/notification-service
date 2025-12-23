package com.notification.api.controller.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.common.sdk.models.interfaces.GenericApiResponse;
import com.common.sdk.services.ResponseHandler;
import com.notification.api.models.request.CreateTemplateRequest;
import com.notification.api.models.request.TemplateFilterRequest;
import com.notification.api.models.request.UpdateTemplateRequest;
import com.notification.api.models.response.FilterTemplateResponse;
import com.notification.api.models.response.TemplateResponse;
import com.notification.api.services.interfaces.TemplateService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/template")
public class TemplateController {
	
	private final ResponseHandler responseHandler = new ResponseHandler();

	@Autowired
	private TemplateService templateService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GenericApiResponse<TemplateResponse> createTemplate( @Valid @RequestBody CreateTemplateRequest request)
	{
		return responseHandler.ok(templateService.createTemplate(request));
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public GenericApiResponse<FilterTemplateResponse> filterTemplate(TemplateFilterRequest request)
	{
		return responseHandler.ok(templateService.filterTemplate(request));
	}
	
	
	@PatchMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public GenericApiResponse<TemplateResponse> updateTemplate(@PathVariable String id, @Valid @RequestBody UpdateTemplateRequest request)
	{
		
		return responseHandler.ok(templateService.updateTemplate(id,request));
	}
	
	
	@PatchMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public GenericApiResponse<String> deleteTemplate(@PathVariable String id)
	{
		templateService.deleteTemplate(id);
		return responseHandler.ok("Template deleted successfully");
	}
	

}
