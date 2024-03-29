package com.lng.util;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mobile.device.DeviceResolverHandlerInterceptor;
import org.springframework.mobile.device.site.SitePreferenceHandlerInterceptor;
import org.springframework.mobile.device.site.SitePreferenceHandlerMethodArgumentResolver;
import org.springframework.mobile.device.view.LiteDeviceDelegatingViewResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@Configuration
public class AppConf extends WebMvcConfigurerAdapter {
	@Bean
	public DeviceResolverHandlerInterceptor deviceResolverHandlerInterceptor() {
		return new DeviceResolverHandlerInterceptor();
	}

	@Bean
	public SitePreferenceHandlerInterceptor sitePreferenceHandlerInterceptor() {
		return new SitePreferenceHandlerInterceptor();
	}

	@Bean
	public SitePreferenceHandlerMethodArgumentResolver sitePreferenceHandlerMethodArgumentResolver() {
		return new SitePreferenceHandlerMethodArgumentResolver();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(deviceResolverHandlerInterceptor());
		registry.addInterceptor(sitePreferenceHandlerInterceptor());
	}

	@Override
	public void addArgumentResolvers(
			List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(sitePreferenceHandlerMethodArgumentResolver());
	}

	@Bean
	public LiteDeviceDelegatingViewResolver liteDeviceAwareViewResolver() {
		InternalResourceViewResolver delegate = new InternalResourceViewResolver();
		delegate.setPrefix("/jsp/");
		delegate.setSuffix(".jsp");
		LiteDeviceDelegatingViewResolver resolver = new LiteDeviceDelegatingViewResolver(
				delegate);
		resolver.setMobilePrefix("../jspM/");
		resolver.setTabletPrefix("../jspM/");
//		resolver.setMobilePrefix("/");
//		resolver.setTabletPrefix("/");
		
		return resolver;
	}
}
