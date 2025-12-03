package com.notification.processor.models.entity;

import java.util.Map;
import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Document(collection="templates")
@EqualsAndHashCode(callSuper=true)
public class Template extends AbstractEntity{

	private String id;
	private String name;

	private Map<String,String> templateVariables;
	private String  messageTemplate;
	private String tenantId;
	
}
