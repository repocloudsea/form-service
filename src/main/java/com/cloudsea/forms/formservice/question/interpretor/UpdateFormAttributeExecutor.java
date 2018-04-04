package com.cloudsea.forms.formservice.question.interpretor;

import com.cloudsea.forms.formservice.question.converters.Converter;
import com.cloudsea.forms.formservice.question.dto.UpdateForm;
import com.cloudsea.forms.formservice.question.model.Form;
import com.cloudsea.forms.formservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

public class UpdateFormAttributeExecutor implements PatchExecutor {

    @Override
    public void performPatch(Form form, UpdateForm updateForm, Map<String, Converter> converters) {
        String attributeName = updateForm.getPath().split("/")[1];
        Utils.patchProperty(form, updateForm, attributeName, attributeName, converters);
    }
}
