package com.notification.api.services.implementation;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.notification.api.constants.ErrorConstants;
import com.notification.api.dao.interfaces.TemplateDao;
import com.notification.api.exception.ValidationException;
import com.notification.api.models.context.NotificationContext;
import com.notification.api.models.context.NotificationContextHolder;
import com.notification.api.models.entity.Template;
import com.notification.api.models.request.CreateTemplateRequest;
import com.notification.api.models.request.TemplateFilterRequest;
import com.notification.api.models.request.UpdateTemplateRequest;
import com.notification.api.models.response.FilterTemplateResponse;
import com.notification.api.models.response.TemplateResponse;
import com.notification.api.models.response.TemplateResponseDTO;
import com.notification.api.services.interfaces.TemplateService;
import com.notification.api.utils.CommonUtils;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import static com.notification.api.constants.ErrorConstants.TEMPLATE_ALREADY_EXISTS_ERROR;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
class TemplateServiceImpl implements TemplateService {
	
	private final TemplateDao templateDao;

	@Override
	public TemplateResponse createTemplate(CreateTemplateRequest request) {
		
	//	NotificationContext context =NotificationContextHolder.getContext();
		
		templateDao.findByTenantIdAndName(CommonUtils.getCurrentTenantId(), request.getName()).ifPresent(tenant -> 
		{
			throw new ValidationException(TEMPLATE_ALREADY_EXISTS_ERROR,HttpStatus.BAD_REQUEST.value());
		});
		
		
		Template template=new Template();
		template.setId(CommonUtils.generateUUID());
		template.setTenantId(CommonUtils.getCurrentTenantId());
		BeanUtils.copyProperties(request, template);
		template.entityCreated();
		templateDao.save(template);
		return new TemplateResponse(template);
	}

	@Override
	public FilterTemplateResponse filterTemplate(TemplateFilterRequest request) {
		//NotificationContextHolder.ignoreTenantIdInjection();
		Page<Template> templates=templateDao.filterTemplate(request.buildSearch(),request.buildPageRequest());
	List<TemplateResponseDTO> data=	templates.stream().map(e -> new TemplateResponseDTO(e.getId()+"",e.getName(),e.getTemplateVariables())).toList();
		return new FilterTemplateResponse(data,templates.hasNext(),templates.getTotalElements());
	}

	@Override
	public TemplateResponse updateTemplate(String id, UpdateTemplateRequest request) {
		Template template=getTemplateForCurrentTenant(id);
		if(CommonUtils.isNotEmpty(request.getName()))
		{
			templateDao
			.findByTenantIdAndName(CommonUtils.getCurrentTenantId(), request.getName())
			.ifPresent((t) -> {throw new ValidationException(ErrorConstants.TEMPLATE_ALREADY_EXISTS_ERROR,HttpStatus.BAD_REQUEST.value());});
			template.setName(request.getName());
		}
		
		
		if(CommonUtils.isNotEmpty(request.getMessageTemplate())) {
			template.setMessageTemplate(request.getMessageTemplate());
	}
		if(CommonUtils.isNotEmpty(request.getTemplateVariables())) {
			template.setTemplateVariables(request.getTemplateVariables());
	}
		templateDao.save(template);
		return new TemplateResponse(template);
	}

	@Override
	public void deleteTemplate(String id) {
		
		getTemplateForCurrentTenant(id);
		templateDao.deleteTemplateById(id);
	}
	
	
	private Template getTemplateForCurrentTenant(String id)
	{
		return templateDao.findByTenantIdAndId(CommonUtils.getCurrentTenantId(), id).orElseThrow(() -> new ValidationException(ErrorConstants.TEMPLATE_NOT_EXISTS_WITH_ID_ERROR,HttpStatus.BAD_REQUEST.value()));
	}
	

}
