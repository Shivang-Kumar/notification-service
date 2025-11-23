package com.notification.api.dao.interfaces.implementation;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.notification.api.dao.interfaces.TemplateDao;
import com.notification.api.models.entity.Template;
import com.notification.api.repositories.TemplateRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
@RequiredArgsConstructor
class TemplateDaoImpl implements TemplateDao{

	
	private final TemplateRepository templateRepository;
	@Override
	public Optional<Template> findByTenantIdAndName(String tenantId,String templateName) {
		return templateRepository.findByNameIgnoreCaseAndTenantId(templateName, UUID.fromString(tenantId));
	}
	
	@Override
	public void save(Template template) {
		 templateRepository.save(template);	
	}
	
}
