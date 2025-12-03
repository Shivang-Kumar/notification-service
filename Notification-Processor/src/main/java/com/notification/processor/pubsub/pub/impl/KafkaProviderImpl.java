package com.notification.processor.pubsub.pub.impl;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.notification.processor.pubsub.pub.interfaces.KafkaProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnProperty(value="messaging.providers.kafka.enabled",havingValue ="true")

public class KafkaProviderImpl implements KafkaProvider{

	
	private final KafkaTemplate<String,String> kafkaTemplate;
	
	@Override
	public boolean sendNotification(String topic, String message) {
		try {
			kafkaTemplate.send(topic,message);
			log.info("Kafka Data Published to Topic: {}",topic);
			return true;
		} catch (Exception e) {
			log.info("Error while publishing data to Kafka for Topic:{} with Error: {}",topic,e);
			return false;
		}
	}

}
