package com.notification.api.pubsub.pubsub.publisher;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.stereotype.Service;

import com.notification.api.pubsub.fallback.GenericFallBackPublisher;
import com.notification.api.pubsub.primary.GenericProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class GenericPublisherImpl implements GenericPublisher {

	
	private List<GenericProvider> genericPublisher;
	private List<GenericFallBackPublisher> genericFallBackPublishers;
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
