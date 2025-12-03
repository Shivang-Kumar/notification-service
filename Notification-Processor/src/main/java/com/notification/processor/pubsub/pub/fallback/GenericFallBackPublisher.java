package com.notification.processor.pubsub.pub.fallback;

public interface GenericFallBackPublisher {
	
	boolean sendNotification(String topic,String message);

}
