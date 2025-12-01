package com.notification.api.constants;

public interface ErrorConstants {
	
	String TEMPLATE_ALREADY_EXISTS_ERROR="Template already exists with given number";
	String TEMPLATE_NOT_EXISTS_WITH_ID_ERROR="Template dosent exists with given id";
	String TEMPLATED_ID_IS_REQUIRED="Template Id is Required";
	String PUT_CACHING_ERROR="Error while caching data";
    String TEMPLATE_REDIS_PREFIX="templates.";
	String CACHE_PARSING_ERROR = "Error while parsing Cache data";
}