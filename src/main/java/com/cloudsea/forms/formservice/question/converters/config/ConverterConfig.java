package com.cloudsea.forms.formservice.question.converters.config;

import com.cloudsea.forms.formservice.question.converters.Converter;
import com.cloudsea.forms.formservice.question.converters.ElementConverter;
import com.cloudsea.forms.formservice.question.model.FormStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ConverterConfig {

    private static String convert(String value) {
        return value;
    }

    @Bean
    public Map<String, Converter> getConverters() {
        Map<String, Converter> converters = new HashMap<>();
        converters.put("title", value -> convert(value));
        converters.put("status", name -> FormStatus.valueOf(name));
        converters.put("required", s -> Boolean.valueOf(s));
        converters.put("elements", new ElementConverter());
        return converters;
    }
}
