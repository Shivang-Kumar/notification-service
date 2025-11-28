package com.notification.api.pubsub.implementation;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.notification.api.pubsub.interfaces.KafkaProvider;
import com.notification.api.pubsub.interfaces.RabbitMqProvider;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@ConditionalOnProperty(value="messaging.fallback.rabbitmq.enabled", havingValue = "true")
public class RabbitMqProviderImpl implements RabbitMqProvider{

	@Override
	public boolean sendNotification(String topic, String message) {
		
		log.info("Sending Notification Using RabbitMq for Topic:{} For Message {}",topic,message);
		return false;
	}

}
