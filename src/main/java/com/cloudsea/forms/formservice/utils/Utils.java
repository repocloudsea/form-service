package com.cloudsea.forms.formservice.utils;

import java.util.*;
import java.util.stream.Collectors;

import com.cloudsea.forms.formservice.question.controller.FormConfigureController;
import com.cloudsea.forms.formservice.question.converters.Converter;
import com.cloudsea.forms.formservice.question.model.Element;
import com.cloudsea.forms.formservice.question.model.Form;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {

    private static final Logger LOG = LoggerFactory.getLogger(FormConfigureController.class);

    public static Map<String, Element> getElementMap(List<Element> elements) {
        return elements.stream().collect(Collectors.toMap(Element::getRefId, e -> e));
    }

    public static String getElementType(Element elm) {
        return elm.getClass().getAnnotation(JsonTypeName.class).value();
    }

    public static int getIndexOfElement(Form form, String elementId) {

        LOG.info(" Finding element-id {} in form {} ", elementId, form);

        Optional<Element> element = form.getElements()
                .stream()
                .filter(elm -> elm.getRefId().equals(elementId))
                .peek(elm -> LOG.info("Element found {} ", elm))
                .findAny();
        return element.isPresent() ? form.getElements().indexOf(element.get()) : -1;
    }

    public static String getElementPath(int index, String attribute) {
        return String.format("elements[%d].%s", index, attribute);
    }

    public static void populateConverters(Map<String, Converter> converters) {

        for (PojoClass clazz : PojoClassFactory.enumerateClassesByExtendingType
                ("com.cloudsea.forms.formservice.question.model", Element.class, null)) {
            try {
                populateConverters(converters, Class.forName(clazz.getName()));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        // pouplate converters for Form.class
        populateConverters(converters,Form.class);
    }

    private static void populateConverters(Map<String, Converter> converters, Class clazz) {

        Arrays.asList(clazz.getDeclaredFields()).stream()
                .filter(field -> field.getType() == String.class)
                .forEach(field -> converters.put(field.getName(), value -> convert(value)));

        Arrays.asList(clazz.getDeclaredFields()).stream()
                .filter(field -> field.getType() == Boolean.class)
                .forEach(field -> converters.put(field.getName(), value -> Boolean.valueOf(value)));

        Arrays.asList(clazz.getDeclaredFields()).stream()
                .filter(field -> field.getType() == Integer.class)
                .forEach(field -> converters.put(field.getName(), value -> Integer.valueOf(value)));
    }

    private static String convert(String value) {
        return value;
    }


}
