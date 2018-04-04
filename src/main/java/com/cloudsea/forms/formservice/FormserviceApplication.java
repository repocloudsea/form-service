package com.cloudsea.forms.formservice;

import com.cloudsea.forms.formservice.question.model.Form;
import com.cloudsea.forms.formservice.question.service.FormsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
@CrossOrigin
public class FormserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FormserviceApplication.class, args);
    }
}


@Component
class DataBuilder implements CommandLineRunner {

    @Autowired
    private FormsService formsService;

    @Override
    public void run(String... args) throws Exception {

        Form form = new Form();
        form.setTitle("First");
        form.setUserId("shahbaz");
        formsService.create(form);

    }
}