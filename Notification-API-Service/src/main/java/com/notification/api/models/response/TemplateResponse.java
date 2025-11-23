package com.notification.api.models.response;

import com.notification.api.models.entity.Template;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateResponse {
   private String id;
   private String name;
   
   public TemplateResponse(Template template)
   {
	   setId(template.getId().toString());
	   setName(template.getName());
   }
   
}
