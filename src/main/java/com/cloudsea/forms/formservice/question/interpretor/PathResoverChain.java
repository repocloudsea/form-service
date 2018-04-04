package com.cloudsea.forms.formservice.question.interpretor;

public interface PathResoverChain {

    public void nextResolver(PathResoverChain nextPathResoverChain);

    public PatchExecutor resolve(String path);

}
