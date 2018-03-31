package com.cloudsea.forms.formservice.question.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.cloudsea.forms.formservice.question.converters.Converter;
import com.cloudsea.forms.formservice.question.dto.UpdateForm;
import com.cloudsea.forms.formservice.question.model.Element;
import com.cloudsea.forms.formservice.utils.Utils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudsea.forms.formservice.question.model.Form;
import com.cloudsea.forms.formservice.question.repository.FormRepository;
import com.cloudsea.forms.formservice.validate.Validate;

@Service
public class FormsService {

    private FormRepository formRepository;
    private final Map<String, Converter> converters;

    @Autowired
    public FormsService(FormRepository formRepository, Map<String, Converter> converters) {
        this.formRepository = formRepository;
        this.converters = converters;
    }

    public void create(Form form) {
        if (form == null)
            throw new IllegalArgumentException("Form cannot be null");

        if (form.getElements() != null) {

            form.getElements()
                    .stream()
                    .forEach(Validate::validateElements);
        }

        formRepository.save(form);
    }

    public Form findById(String id) {
        if (StringUtils.isBlank(id))
            throw new IllegalArgumentException("ID Cannot be null or blank");
        return formRepository.findOne(id);
    }

    public List<Form> findAll() {
        return formRepository.findAll();
    }

    public List<Form> findByTitle(String title) {
        if (StringUtils.isBlank(title))
            throw new IllegalArgumentException("Title cannot be blank");
        return formRepository.findByTitle(title);
    }

    public List<Form> findByUserId(String userId) {
        if (StringUtils.isBlank(userId))
            throw new IllegalArgumentException("User cannot be blank");
        return formRepository.findByUserId(userId);
    }

    public void patch(Form formDb, UpdateForm updateForm) {


        String path = updateForm.getPath();
        String updatePath = "";


        if (path.contains("elements") && path.contains("id")) {

            String[] pathArr = path.split("/");
            String id = pathArr[2];
            int elementIndex = Utils.getIndexOfElement(formDb, id);
            updatePath = Utils.getElementPath(elementIndex, pathArr[3]);
            patchProperty(formDb, updateForm, updatePath, pathArr[3]);

        } else if (path.contains("elements")) {
            updatePath = path.split("/")[1];

            Converter<Element> converter = converters.get(updatePath);
            Element element = converter.convert(updateForm.getValue());
            formDb.getElements().add(element);

        } else {
            updatePath = path.split("/")[1];
            patchProperty(formDb, updateForm, updatePath, updatePath);
        }


        formRepository.save(formDb);

    }

    private void patchProperty(Form formDb, UpdateForm updateForm, String attributePath, String attributeName) {
        try {
            PropertyUtils.setProperty(formDb, attributePath, converters.get(attributeName).convert(updateForm.getValue()));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
