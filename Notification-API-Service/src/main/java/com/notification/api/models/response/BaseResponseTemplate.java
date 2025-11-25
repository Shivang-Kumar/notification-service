package com.notification.api.models.response;

import java.util.List;

import lombok.Setter;

@Setter
public class BaseResponseTemplate<I,R extends Number> {
	
	private List<I> data;
	private boolean hasMoreData;
	private R totalCount;
	

}
