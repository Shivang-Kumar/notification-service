package com.notification.processor.models.request;

import com.notification.processor.models.entity.Template;

import lombok.Data;

@Data
public class TemplateFilterRequest extends BaseSearchDto<Template> {
	
	String name;

	@Override
	public Class<Template> getEntity() {
		return Template.class;
	}

}
