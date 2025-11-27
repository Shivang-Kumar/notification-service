package com.notification.api.utils;

import java.util.Calendar;
import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.util.ObjectUtils;

import com.notification.api.constants.ApplicationConstants;
import com.notification.api.models.context.NotificationContextHolder;

public final class CommonUtils {
	
	private static final Calendar calendar=Calendar.getInstance();
	public static long getCurrentTimeStamp()
	{
		return calendar.getTimeInMillis();
	}
	
	
	
	public static boolean isNotEmpty(Object input) {
		return ObjectUtils.isEmpty(input);
	}

	public static boolean isEmpty(Object input) {
		return ObjectUtils.isEmpty(input);
	}
	
	public static String generateUUID()
	{
		return UUID.randomUUID().toString();
	}



	public static String getCurrentTenantId() {
		return NotificationContextHolder.getContext().tenantId();
	}
	
	public static String getCurrentTraceId() {
		return MDC.get(ApplicationConstants.X_REQUEST_ID);
	}
	
}
