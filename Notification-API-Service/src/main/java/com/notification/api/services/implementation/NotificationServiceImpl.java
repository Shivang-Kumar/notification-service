package com.notification.api.services.implementation;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.notification.api.constants.ErrorConstants;
import com.notification.api.dao.interfaces.TemplateDao;
import com.notification.api.exception.ValidationException;
import com.notification.api.models.entity.Template;
import com.notification.api.models.request.IngestTopicDto;
import com.notification.api.models.request.SendNotificationRequest;
import com.notification.api.pubsub.pubsub.publisher.GenericPublisher;
import com.notification.api.services.interfaces.NotificationService;
import com.notification.api.utils.CommonUtils;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements  NotificationService{

	private TemplateDao templateDao;
	private GenericPublisher genericPublisher;
	
	@Override
	public void sendNotification(SendNotificationRequest request)
	{
	  Optional<Template> byTenantIdAndId=	templateDao.findByTenantIdAndId(CommonUtils.getCurrentTenantId(), request.getTemplateId());
	  
	  if(byTenantIdAndId.isEmpty())
	  {
		  //Todo send to audit topic
		  
		 // genericPublisher.sendDataToAudit(byTenantIdAndId);

		  throw new ValidationException(ErrorConstants.TEMPLATE_NOT_EXISTS_WITH_ID_ERROR,HttpStatus.BAD_REQUEST.value());
	  }
	  
	  
	  byTenantIdAndId.ifPresent(template -> {
		  
		  Map<String,Object> requestDynamicVariables=request.getDynamicVariables();
		  if(template.getTemplateVariables().size()!=request.getDynamicVariables().size()
				  || template.getTemplateVariables().values().stream().anyMatch(variable -> ! requestDynamicVariables.containsKey(variable)))
				  {
			  throw new ValidationException("Invalid Dynamic Varibales");
		  }
	  });
	  
	 
	  
	  IngestTopicDto ingestTopicDto=new IngestTopicDto();
	  ingestTopicDto.setRequestId(CommonUtils.getCurrentTraceId());
	  ingestTopicDto.setTenantId(CommonUtils.getCurrentTenantId());
	  ingestTopicDto.setReceivedAt(CommonUtils.getCurrentTimeStamp());
	  ingestTopicDto.setTemplateId(request.getTemplateId());
	  ingestTopicDto.setDynamicVariables(request.getDynamicVariables());
	  ingestTopicDto.setNotificationType(request.getNotifocationType());
	  
	  genericPublisher.sendDataToIngest(ingestTopicDto);
	  


	  
	  
	}
	
}
