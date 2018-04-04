package com.cloudsea.forms.formservice.question.interpretor;

import com.cloudsea.forms.formservice.question.converters.Converter;
import com.cloudsea.forms.formservice.question.dto.UpdateForm;
import com.cloudsea.forms.formservice.question.model.Element;
import com.cloudsea.forms.formservice.question.model.Form;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

public class AddElementExecutor implements PatchExecutor {

    @Override
    public void performPatch(Form form, UpdateForm updateForm, Map<String, Converter> converters) {

        String attributeName = updateForm.getPath().split("/")[1];

        Converter<Element> converter = converters.get(attributeName);
        Element element = converter.convert(updateForm.getValue());
        form.getElements().add(element);
    }
}
