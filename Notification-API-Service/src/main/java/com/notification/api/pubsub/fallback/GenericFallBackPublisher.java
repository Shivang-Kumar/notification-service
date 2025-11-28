package com.notification.api.pubsub.fallback;

public interface GenericFallBackPublisher {
	
	boolean sendNotification(String topic,String message);

}
