package com.notification.api.pubsub.pubsub.publisher;

public interface GenericPublisher {
	
	void sendNotification(String topic,String message);

}
