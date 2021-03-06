package com.cloudsea.forms.formservice.question.interpretor;

import com.cloudsea.forms.formservice.question.converters.Converter;
import com.cloudsea.forms.formservice.question.dto.UpdateForm;
import com.cloudsea.forms.formservice.question.model.Form;
import com.cloudsea.forms.formservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;

public class UpdateElementExecutor implements PatchExecutor {


    @Override
    public void performPatch(Form form, UpdateForm updateForm, Map<String,Converter> converters) {

        String[] pathArr = updateForm.getPath().split("/");
        String id = pathArr[2];
        String attributeName = pathArr[3];
        int elementIndex = Utils.getIndexOfElement(form, id);
        String attributeNameWithIndex = Utils.getElementPath(elementIndex, attributeName);
        Utils.patchProperty(form, updateForm, attributeNameWithIndex, attributeName, converters);


    }
}
