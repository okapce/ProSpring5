package com.prospring.ch16.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.ui.context.support.ResourceBundleThemeSource;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.theme.CookieThemeResolver;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.prospring.ch16"}//, 
		//excludeFilters = { 
	    	//@Filter(type = FilterType.ANNOTATION, value = Configuration.class)}
		)
public class WebConfig implements WebMvcConfigurer {
	
	//Declare the static resources.
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/").setCachePeriod(31556926);
	}
	
	@Bean
	InternalResourceViewResolver viewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("webapp/WEB-INF/views"); resolver.setSuffix(".jspx" );
		resolver.setRequestContextAttribute("requestContext"); return resolver; 
	}
	
	// <=> <mvc:view-controller .../>
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("singers/list");
	}
	
	// <=> <mvc:default-servlet-handler/>
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	 //registry.addInterceptor(localeChangeInterceptor());
	 registry.addInterceptor(themeChangeInterceptor());
	}
	
	@Bean
	ThemeChangeInterceptor themeChangeInterceptor() {
	 return new ThemeChangeInterceptor();
	}
	
	@Bean
	ResourceBundleThemeSource themeSource() {
	 return new ResourceBundleThemeSource();
	}
	
	/*
	@Bean
	CookieThemeResolver themeResolver() {
	 CookieThemeResolver cookieThemeResolver = new CookieThemeResolver();
	 cookieThemeResolver.setDefaultThemeName("standard");
	 cookieThemeResolver.setCookieMaxAge(3600);
	 cookieThemeResolver.setCookieName("theme");
	 return cookieThemeResolver;
	}
	*/

}
