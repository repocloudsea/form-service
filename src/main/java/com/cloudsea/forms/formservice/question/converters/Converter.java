package com.cloudsea.forms.formservice.question.converters;

import com.cloudsea.forms.formservice.question.model.Element;

public interface Converter<T> {

    T convert(String value) throws IllegalArgumentException;
}
