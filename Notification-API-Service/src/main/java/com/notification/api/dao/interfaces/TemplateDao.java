package com.notification.api.dao.interfaces;

import java.util.Optional;

import com.notification.api.models.entity.Template;

public interface TemplateDao {

	Optional<Template> findByTenantIdAndName(String tenantId, String templateName);

	void save(Template template);

}
