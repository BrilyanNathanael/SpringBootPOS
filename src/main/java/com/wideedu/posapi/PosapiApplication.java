package com.wideedu.posapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

import com.wideedu.posapi.filter.AuthenticateFilter;

@ServletComponentScan
@SpringBootApplication
public class PosapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PosapiApplication.class, args);
	}

}
