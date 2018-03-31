package com.cloudsea.forms.formservice.question.converters;

import com.cloudsea.forms.formservice.question.model.Element;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ElementConverter implements Converter<Element> {

    @Override
    public Element convert(String value) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(value, Element.class);

        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(" JSON cannot pe marshalled to Element");
        }
    }
}
