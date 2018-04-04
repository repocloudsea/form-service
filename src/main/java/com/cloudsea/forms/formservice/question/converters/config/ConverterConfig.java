package com.cloudsea.forms.formservice.question.converters.config;

import com.cloudsea.forms.formservice.question.converters.Converter;
import com.cloudsea.forms.formservice.question.converters.ElementConverter;
import com.cloudsea.forms.formservice.question.interpretor.UpdateFormAttributeResolver;
import com.cloudsea.forms.formservice.question.model.Element;
import com.cloudsea.forms.formservice.question.model.Form;
import com.cloudsea.forms.formservice.question.model.FormStatus;
import com.cloudsea.forms.formservice.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ConverterConfig {

    private static final Logger LOG = LoggerFactory.getLogger(ConverterConfig.class);


    @Bean
    public Map<String, Converter> getConverters() {
        Map<String, Converter> converters = new HashMap<>();

        // Populating converters for String, Boolean and Integer Attributes
        Utils.populateConverters(converters);

        // Adding custom converters
        converters.put("status", name -> FormStatus.valueOf(name));
        converters.put("elements", new ElementConverter());

        LOG.info("Converters {} ->  ", converters);

        return converters;
    }
}
