package com.notification.processor.pubsub.pub.impl;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.notification.processor.pubsub.pub.interfaces.AwsSqsProvider;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@ConditionalOnProperty(value="messaging.providers.aws.enabled",havingValue ="true")
public class AwsSQSProviderImp implements AwsSqsProvider{

	@Override
	public boolean sendNotification(String topic, String message) {

		log.info("Sending Notification using AWS SQS Provider for topic: {} ",topic);
		return true;
	}

}
