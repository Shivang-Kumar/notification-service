package com.notification.processor.config;

	import com.mongodb.ConnectionString;
	import com.mongodb.MongoClientSettings;
	import com.mongodb.client.MongoClient;
	import com.mongodb.client.MongoClients;

import java.util.concurrent.TimeUnit;

import org.bson.UuidRepresentation;
	import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.Configuration;

	@Configuration
	public class MongoConfig {

	    @Bean
	    public MongoClient mongoClient() {
	        ConnectionString connectionString = new ConnectionString(
	            "mongodb://root:root@localhost:27017/?authSource=admin"
	        );

	        MongoClientSettings settings = MongoClientSettings.builder()
	                .applyConnectionString(connectionString)
	                .uuidRepresentation(UuidRepresentation.STANDARD)
	                .applyToSocketSettings(builder -> builder.connectTimeout(5, TimeUnit.SECONDS).readTimeout(5, TimeUnit.SECONDS))
	        		.applyToClusterSettings(builder -> builder.serverSelectionTimeout(5, TimeUnit.SECONDS))
	                .build();

	        return MongoClients.create(settings);
	    }
	}

