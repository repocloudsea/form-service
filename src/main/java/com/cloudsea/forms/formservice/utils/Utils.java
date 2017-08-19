package com.cloudsea.forms.formservice.utils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.cloudsea.forms.formservice.questions.model.Element;

public class Utils {

	public static Map<String, Element> getElementMap(List<Element> elements) {
		return elements.stream().collect(Collectors.toMap(Element::getRefId, e -> e));
	}
}
