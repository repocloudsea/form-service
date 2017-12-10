package com.cloudsea.forms.formservice.request.metadata;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
	private RequestMetadataRepository requestMetadataRepository;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(new HandlerInterceptorAdapter() {

			public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
					throws Exception {

				RequestMetadata requestMetadata = new RequestMetadata();
				requestMetadata.setRemoteIPAddress(request.getRemoteAddr());
				requestMetadata.setUserAgent(request.getHeader("user-agent"));
				requestMetadata.setUserLocale(request.getHeader("accept-language"));
				
				requestMetadataRepository.save(requestMetadata);

				return true;
			};

		}).addPathPatterns("/**");
	}
}