package com.notification.processor.dao.interfaces.implementation;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.notification.processor.dao.interfaces.CacheService;
import com.notification.processor.dao.interfaces.TemplateDao;
import com.notification.processor.models.entity.Template;
import com.notification.processor.dao.repositories.TemplateRepository;
import com.notification.processor.utils.CommonUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
@RequiredArgsConstructor
class TemplateDaoImpl implements TemplateDao{

	
	private final TemplateRepository templateRepository;
	private final CacheService cacheService;
	
	
	@Override
	public Optional<Template> findByTenantIdAndName(String tenantId,String templateName) {
		return cacheService.getByName(tenantId, templateName, Template.class).or(()-> 
			 templateRepository.findByNameIgnoreCaseAndTenantId(templateName,tenantId).map((template) -> {
				 cacheService.putByName(tenantId, templateName, template);
				 return template;
			 }));
	}
	
	@Override
	public Optional<Template> findByTenantIdAndId(String tenantId,String id)
	{
		
		return cacheService.getById(tenantId, id, Template.class).or(()-> 
		templateRepository.findByIdAndTenantId(id,tenantId).map((template) -> {
		 cacheService.putByName(tenantId, id, template);
		 return template;
	 }));
	
	}
	
	@Override
	public void save(Template template) {
		cacheService.putById(template.getTenantId(), template.getId(), template);
		cacheService.putByName(template.getTenantId(), template.getName(), template);
		 templateRepository.save(template);	
	}

	@Override
	public Page<Template> filterTemplate(Example<Template> example, PageRequest pageRequest) {
		Page<Template> template=templateRepository.findAll(example,pageRequest);
		return template;
	}

	@Override
	public void deleteTemplateById(String id) {
		cacheService.getById(CommonUtils.getCurrentTenantId(), id, Template.class).ifPresent((template) -> cacheService.deletedById(CommonUtils.getCurrentTenantId(),template.getId()));
		templateRepository.deleteById(id);
			
	}
	
}
