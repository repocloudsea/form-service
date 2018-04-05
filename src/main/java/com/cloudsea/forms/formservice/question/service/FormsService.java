package com.cloudsea.forms.formservice.question.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.cloudsea.forms.formservice.question.controller.FormConfigureController;
import com.cloudsea.forms.formservice.question.converters.Converter;
import com.cloudsea.forms.formservice.question.dto.Operation;
import com.cloudsea.forms.formservice.question.dto.UpdateForm;
import com.cloudsea.forms.formservice.question.interpretor.PathResoverChain;
import com.cloudsea.forms.formservice.question.interpretor.RemoveElementExecutor;
import com.cloudsea.forms.formservice.question.model.Element;
import com.cloudsea.forms.formservice.utils.Utils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudsea.forms.formservice.question.model.Form;
import com.cloudsea.forms.formservice.question.repository.FormRepository;
import com.cloudsea.forms.formservice.validate.Validate;

@Service
public class FormsService {

    private static final Logger LOG = LoggerFactory.getLogger(FormsService.class);

    private final FormRepository formRepository;
    private final Map<String, Converter> converters;
    private final PathResoverChain pathResover;

    @Autowired
    public FormsService(FormRepository formRepository, Map<String, Converter> converters, PathResoverChain pathResover) {
        this.formRepository = formRepository;
        this.converters = converters;
        this.pathResover = pathResover;
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

        LOG.info("Form data before updating -> {} ", formDb);

        //TODO Both update and delete should happen via PathResolver and PatchExecutor

        if (updateForm.getOperation() == Operation.UPDATE) {
            pathResover.resolve(updateForm.getPath()).performPatch(formDb, updateForm, converters);
        } else {

            // TODO This is a code smell and breaking standard, need to find a way to REMOVE/UPDATE in a better way
            new RemoveElementExecutor().performPatch(formDb, updateForm, converters);
        }


        LOG.info("Form data after patch applied -> {} ", formDb);

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
