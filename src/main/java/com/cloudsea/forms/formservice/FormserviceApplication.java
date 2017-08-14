package com.cloudsea.forms.formservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.config.EnableHypermediaSupport;

@SpringBootApplication
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class FormserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FormserviceApplication.class, args);
	}
}
