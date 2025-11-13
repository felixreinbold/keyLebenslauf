package com.example.lebenslaufUpdate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import Auth.AdminAuthFilter;
import Auth.AuthFilter;

@SpringBootApplication
public class LebenslaufUpdateApplication {
    
    @Bean
    public FilterRegistrationBean<AuthFilter> authFilter() {
        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthFilter());
        registrationBean.addUrlPatterns("/protected/index.html");
        return registrationBean;
    }
    @Bean
    public FilterRegistrationBean<AdminAuthFilter> adminAuthFilter() {
        FilterRegistrationBean<AdminAuthFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AdminAuthFilter());
        registrationBean.addUrlPatterns("/protected/admin.html");
        return registrationBean;
    }

	public static void main(String[] args) {
		SpringApplication.run(LebenslaufUpdateApplication.class, args);
	}
	

}
