package com.cloudsea.forms.formservice.question.interpretor;

import com.cloudsea.forms.formservice.question.controller.FormConfigureController;
import com.cloudsea.forms.formservice.question.converters.Converter;
import com.cloudsea.forms.formservice.question.dto.UpdateForm;
import com.cloudsea.forms.formservice.question.model.Form;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class RemoveElementExecutor implements PatchExecutor {

    private static final Logger LOG = LoggerFactory.getLogger(RemoveElementExecutor.class);


    @Override
    public void performPatch(Form form, UpdateForm updateForm, Map<String, Converter> converters) {

        String updatePathArr[] = updateForm.getPath().split("/");
        String attributeName = updatePathArr[1];
        String id = updatePathArr[2];

        LOG.info("Trying to remove {} with id {} ",attributeName,id);

        form.getElements().removeIf(element -> element.getRefId().equals(id));
    }
}
