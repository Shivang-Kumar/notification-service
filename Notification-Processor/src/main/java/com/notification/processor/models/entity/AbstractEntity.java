package com.notification.processor.models.entity;

import com.notification.processor.utils.CommonUtils;

import lombok.Data;

@Data
public abstract class AbstractEntity {

	private Long createdAt;
	private Long updatedAt;
	
	
	public void entityCreated()
	{
		setCreatedAt(CommonUtils.getCurrentTimeStamp());
		setUpdatedAt(CommonUtils.getCurrentTimeStamp());
	}
	
	public void entityUpdated()
	{
		setUpdatedAt(CommonUtils.getCurrentTimeStamp());
	}
}
