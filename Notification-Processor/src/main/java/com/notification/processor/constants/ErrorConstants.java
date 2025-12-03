package com.notification.processor.constants;

public interface ErrorConstants {
	
	String TEMPLATE_ALREADY_EXISTS_ERROR="Template already exists with given number";
	String TEMPLATE_NOT_EXISTS_WITH_ID_ERROR="Template dosent exists with given id";
	String TEMPLATED_ID_IS_REQUIRED="Template Id is Required";
	String PUT_CACHING_ERROR="Error while caching data";
    String TEMPLATE_REDIS_PREFIX="templates.";
	String CACHE_PARSING_ERROR = "Error while parsing Cache data";
	String TEMPLATE_VARIABLE_ERROR="Template Variables is required! And max Size should be 20";
	String TEMPLATE_NAME_ERROR ="Template Name should be less than 100";
	String TEMPLATE_MESSAGE_ERROR = "Max Message Template Length Should be 10000";
	String NOTIFICATION_TYPE_VARIABLE_ERROR="Notification type is mandatory";
	String SEND_NOTIFICATION_DYNAMIC_VARIABLE_ERROR = "Dynamic Template Variables is required!";
	String KAFKA_EVENT_PARSER_ERROR = "Error while parsing kafka package";
}