package com.notification.processor.models.response;

import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.notification.processor.models.entity.Template;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateResponseDTO {
   private String id;
   private String name;
   private Map<String,String> templateVariables;
   
   public TemplateResponseDTO(Template template)
   {
	   BeanUtils.copyProperties(template, this);
	   setId(template.getId().toString());
   }

}
