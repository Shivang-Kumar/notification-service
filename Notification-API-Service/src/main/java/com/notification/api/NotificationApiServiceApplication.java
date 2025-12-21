package com.notification.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;



@ComponentScan({"com.notification.api","com.common.sdk"})
@SpringBootApplication
public class NotificationApiServiceApplication {

	public static void main(String[] args) {
	
		SpringApplication.run(NotificationApiServiceApplication.class, args);
	}

}
