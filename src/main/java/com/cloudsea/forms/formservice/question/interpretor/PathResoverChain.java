package com.cloudsea.forms.formservice.question.interpretor;

import com.cloudsea.forms.formservice.question.dto.UpdateForm;

public interface PathResoverChain {

    public void nextResolver(PathResoverChain nextPathResoverChain);

    public PatchExecutor resolve(UpdateForm updateForm);

}
