package com.notification.processor.pubsub.pub.publisher;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notification.processor.config.ApplicationProperties;
import com.notification.processor.exception.ValidationException;
import com.notification.processor.pubsub.pub.fallback.GenericFallBackPublisher;
import com.notification.processor.pubsub.pub.primary.GenericProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class GenericPublisherImpl implements GenericPublisher {

	
	private List<GenericProvider> genericPublisher;
	private List<GenericFallBackPublisher> genericFallBackPublishers;
	private ObjectMapper objectMapper;
	private ApplicationProperties properties;
	
	@Override
	public void sendDataToIngest(final Object input)
	{
		String inputInString=convertDataToString(input);
		sendNotification(properties.getIngestTopic(), inputInString);
	}
	
	@Override
	public void sendDataToAudit(final Object input)
	{
		String inputInString=convertDataToString(input);
		sendNotification(properties.getAuditTopic(), inputInString);
	}
	
	
	private String convertDataToString(Object input)
	{
		try {
			return objectMapper.writeValueAsString(input);
		} catch (Exception e) {
			 throw new ValidationException("Error while parsing input payload",HttpStatus.INTERNAL_SERVER_ERROR.value());
		}

	}
	
	
	
	
	AtomicBoolean success=new  AtomicBoolean(false);
	@Override
	public void sendNotification(String topic,String message) {

		log.info("sending notification using generic publisher");
		
		genericPublisher.forEach(publisher -> {
			boolean publisherStatus=publisher.sendNotification(topic, message);
			if(!success.get())
			{
				success.set(publisherStatus);
			}
			if(publisherStatus)
			{
				log.info("notification send to topic : {} using provider {} ",topic,publisher.getClass().getSimpleName());
			}else {
				log.info("Error while publishing data to topic : {} using provider :{} ",topic,publisher.getClass().getSimpleName());
			}
		});
		
		genericFallBackPublishers.forEach(fallback -> {
			if(!success.get())
			{
			boolean publisherStatus=fallback.sendNotification(topic, message);
			if(publisherStatus)
			{
				success.set(true);
				log.info("notification send to topic : {} using fallback provider {} ",topic,fallback.getClass().getSimpleName());
			}else {
				log.info("Error while publishing data to topic : {} using fallback provider :{} ",topic,fallback.getClass().getSimpleName());
			}
			}
		});
		
	}
	
	
}
