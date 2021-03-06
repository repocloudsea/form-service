package com.cloudsea.forms.formservice.question.interpretor;

import com.cloudsea.forms.formservice.question.dto.UpdateForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddElementResolver implements PathResoverChain {

    private static final Logger LOG = LoggerFactory.getLogger(AddElementResolver.class);
    private PathResoverChain nextPathResoverChain;

    @Override
    public void nextResolver(PathResoverChain nextPathResoverChain) {
        this.nextPathResoverChain = nextPathResoverChain;
    }

    @Override
    public PatchExecutor resolve(UpdateForm updateForm) {

        if (updateForm.getPath().contains("elements") && updateForm.getPath().split("/").length == 2) {
            LOG.info("Returning {} ", getClass().getName());
            return new AddElementExecutor();
        } else {
            LOG.info("Checking {} ", nextPathResoverChain.getClass().getName());
            return nextPathResoverChain.resolve(updateForm);
        }

    }
}
