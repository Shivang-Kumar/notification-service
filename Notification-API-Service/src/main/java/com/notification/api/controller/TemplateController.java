package com.notification.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notification.api.models.request.CreateTemplateRequest;
import com.notification.api.models.request.TemplateFilterRequest;
import com.notification.api.models.response.TemplateResponse;
import com.notification.api.services.interfaces.TemplateService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/template")
public class TemplateController {
	
	@Autowired
	private TemplateService templateService;
	
	@PostMapping
	public ResponseEntity<TemplateResponse> createTemplate( @Valid @RequestBody CreateTemplateRequest request)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(templateService.createTemplate(request));
	}
	
	@GetMapping
	public ResponseEntity<Object> filterTemplate(TemplateFilterRequest request)
	{
		return ResponseEntity.ok(templateService.filterTemplate(request));
	}

}
