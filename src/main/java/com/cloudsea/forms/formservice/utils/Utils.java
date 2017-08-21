package com.cloudsea.forms.formservice.utils;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.cloudsea.forms.formservice.questions.model.Element;
import com.cloudsea.forms.formservice.validate.ValidationResult;
import com.fasterxml.jackson.annotation.JsonTypeName;

public class Utils {

	public static Map<String, Element> getElementMap(List<Element> elements) {
		return elements.stream().collect(Collectors.toMap(Element::getRefId, e -> e));
	}

	public static String getElementType(Element elm) {
		return elm.getClass().getAnnotation(JsonTypeName.class).value();
	}
}
