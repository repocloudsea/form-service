package com.cloudsea.forms.formservice.question.interpretor;

import com.cloudsea.forms.formservice.question.dto.UpdateForm;
import com.cloudsea.forms.formservice.question.service.FormsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateFormAttributeResolver implements PathResoverChain {

    private static final Logger LOG = LoggerFactory.getLogger(UpdateFormAttributeResolver.class);

    private PathResoverChain nextPathResoverChain;

    @Override
    public void nextResolver(PathResoverChain nextPathResoverChain) {
        this.nextPathResoverChain = nextPathResoverChain;

    }

    @Override
    public PatchExecutor resolve(UpdateForm updateForm) {

        if (updateForm.getPath().split("/").length == 2) {
            LOG.info("Returning {} ", getClass().getName());
            return new UpdateFormAttributeExecutor();
        } else {
            LOG.info("Checking {} ", nextPathResoverChain.getClass().getName());
            return nextPathResoverChain.resolve(updateForm);
        }

    }
}
