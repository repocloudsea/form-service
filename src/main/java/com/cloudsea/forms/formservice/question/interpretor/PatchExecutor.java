package com.cloudsea.forms.formservice.question.interpretor;

import com.cloudsea.forms.formservice.question.converters.Converter;
import com.cloudsea.forms.formservice.question.dto.UpdateForm;
import com.cloudsea.forms.formservice.question.model.Form;

import java.util.Map;

public interface PatchExecutor {

    public void performPatch(Form form, UpdateForm updateForm, Map<String, Converter> converters);
}
