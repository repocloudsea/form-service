package com.cloudsea.forms.formservice.question.interpretor;

import com.cloudsea.forms.formservice.question.dto.Operation;
import com.cloudsea.forms.formservice.question.dto.UpdateForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RemoveElementResolver implements PathResoverChain {

    private static final Logger LOG = LoggerFactory.getLogger(RemoveElementResolver.class);

    private PathResoverChain nextPathResoverChain;

    @Override
    public void nextResolver(PathResoverChain nextPathResoverChain) {
        this.nextPathResoverChain = nextPathResoverChain;
    }

    @Override
    public PatchExecutor resolve(UpdateForm updateForm) {
        if (updateForm.getOperation() == Operation.REMOVE)
            return new RemoveElementExecutor();
        else
            return nextPathResoverChain.resolve(updateForm);
    }
}
