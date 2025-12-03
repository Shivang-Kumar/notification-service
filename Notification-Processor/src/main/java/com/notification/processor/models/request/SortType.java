package com.notification.processor.models.request;

import lombok.Getter;

@Getter
public enum SortType {
	
	ASC("ASC"),
	DESC("DESC");
	
	
	private String value;
	
	SortType(String value)
	{
		this.value=value;
	}
	
	

}
