package com.notification.processor.pubsub.sub;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import com.notification.processor.services.interfaces.IngestService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class KafkaConsumer {
	
	private final IngestService ingestService;
	@KafkaListener(topics= {"${kafka.ingest.topic}"}, groupId="INGESTION_PROCESSOR_GROUP")
	public void processPackage(String packet)
	{
		ingestService.processPacket(packet);
	}

}
