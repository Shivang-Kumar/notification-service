package com.notification.api.utils;

import java.util.Calendar;
import java.util.UUID;

import org.springframework.util.ObjectUtils;

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
	
	public static UUID generateUUID()
	{
		return UUID.randomUUID();
	}
	
}
