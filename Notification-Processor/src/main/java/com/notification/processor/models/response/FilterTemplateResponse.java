package com.notification.processor.models.response;

import java.util.List;

import lombok.Data;


@Data
public class FilterTemplateResponse extends BaseResponseTemplate<TemplateResponseDTO,Long> {
	
	public FilterTemplateResponse(List<TemplateResponseDTO> list, boolean hasMoreElements , Long totalCount)
	{
		setData(list);
		setHasMoreData(hasMoreElements);
		setTotalCount(totalCount);
	}

	

}
