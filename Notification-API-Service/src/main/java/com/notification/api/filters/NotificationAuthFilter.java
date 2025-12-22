package com.notification.api.filters;

import java.io.IOException;

import org.jboss.logging.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.notification.api.constants.ApplicationConstants;
import com.notification.api.models.context.NotificationContext;
import com.notification.api.models.context.NotificationContextHolder;
import com.notification.api.utils.CommonUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static com.notification.api.constants.ApplicationConstants.X_TENANT_ID;


@Component
class NotificationAuthFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		if(isValidApi(request.getRequestURI())) {
			String xTenantId=request.getHeader(X_TENANT_ID);
			if(CommonUtils.isEmpty(xTenantId))
			{
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				response.getWriter().write("Unauthorized! API key is Required");
				return;
			}
			
//			String requestId=CommonUtils.generateUUID();
//			MDC.put(ApplicationConstants.X_REQUEST_ID, requestId);
//			response.setHeader(ApplicationConstants.X_REQUEST_ID, requestId);	
			NotificationContextHolder.setContext(new NotificationContext(xTenantId,false));
		}
		filterChain.doFilter(request, response);
		
		if(isValidApi(request.getRequestURI()))
		{
			NotificationContextHolder.clear();
//			MDC.clear();
//			
		}
		
	}
	
	static boolean isValidApi(String apiPath)
	{
		return apiPath.startsWith("/api");
	}
	
}
