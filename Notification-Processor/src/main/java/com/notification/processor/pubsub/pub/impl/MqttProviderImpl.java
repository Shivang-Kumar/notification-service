package com.notification.processor.pubsub.pub.impl;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.notification.processor.pubsub.pub.interfaces.MqttProvider;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@ConditionalOnProperty(value="messaging.fallback.mqtt.enabled",havingValue ="true")

public class MqttProviderImpl implements MqttProvider{

	@Override
	public boolean sendNotification(String topic, String message) {
		log.info("Sending Notification using MQTT Provider for topic: {} ",topic);
		return false;
	}

}
