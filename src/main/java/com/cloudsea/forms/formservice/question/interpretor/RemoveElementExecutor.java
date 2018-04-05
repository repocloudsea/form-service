package com.cloudsea.forms.formservice.question.interpretor;

import com.cloudsea.forms.formservice.question.converters.Converter;
import com.cloudsea.forms.formservice.question.dto.UpdateForm;
import com.cloudsea.forms.formservice.question.model.Form;

import java.util.Map;

public class RemoveElementExecutor implements PatchExecutor {

    @Override
    public void performPatch(Form form, UpdateForm updateForm, Map<String, Converter> converters) {

        String updatePathArr[] = updateForm.getPath().split("/");
        String attributeName = updatePathArr[1];
        String id = updatePathArr[2];
        form.getElements().removeIf(element -> element.getRefId().equals(id));
    }
}
