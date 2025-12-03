package com.notification.processor.models.request;

import java.util.Map;

import com.notification.processor.constants.ErrorConstants;
import com.notification.processor.exception.ValidationException;
import com.notification.processor.utils.CommonUtils;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SendNotificationRequest {

	@NotBlank(message = ErrorConstants.TEMPLATED_ID_IS_REQUIRED)
	private String templateId;
	private Map<String, Object> dynamicVariables;
	private NotificationType notifocationType;

	@AssertTrue
	public boolean validateNotificationType() {
		if (CommonUtils.isEmpty(notifocationType)) {
			throw new ValidationException(ErrorConstants.NOTIFICATION_TYPE_VARIABLE_ERROR);
		}

		if (CommonUtils.isEmpty(dynamicVariables))
			throw new ValidationException(ErrorConstants.SEND_NOTIFICATION_DYNAMIC_VARIABLE_ERROR);

		return true;
	}
}
