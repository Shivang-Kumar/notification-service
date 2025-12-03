package com.notification.processor.services.implementation;

import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.notification.processor.constants.ApplicationConstants;
import com.notification.processor.constants.ErrorConstants;
import com.notification.processor.dao.interfaces.TemplateDao;
import com.notification.processor.models.request.IngestTopicDto;
import com.notification.processor.pubsub.pub.publisher.GenericPublisher;
import com.notification.processor.services.interfaces.IngestService;

import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class IngestServiceImpl implements IngestService{
	
	
	private final TemplateDao templateDao;
	private final ObjectMapper objectMapper;
	private final GenericPublisher genericPublisher;
	
	@Override
	public void processPacket(String packet)
	{
		try {
			IngestTopicDto ingestTopicDto= parseDto(packet,IngestTopicDto.class);
			MDC.put(ApplicationConstants.X_REQUEST_ID,ingestTopicDto.getRequestId());

		}
		catch(Exception e){
			//publish to audit
		//	genericPublisher.sendDataToAudit(e);
		}
		
	}
	
	public <T> T  parseDto(String packet,Class<T> clazz)
	{
		try {
			return objectMapper.readValue(packet, clazz);
		} catch (JsonProcessingException e) {
		 throw new ValidationException(ErrorConstants.KAFKA_EVENT_PARSER_ERROR);
		}
	}

}
