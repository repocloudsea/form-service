package com.cloudsea.forms.formservice.question.interpretor;

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
    public PatchExecutor resolve(String path) {

        if (path.contains("elements") && path.split("/").length == 2) {
            LOG.info("Returning {} ", getClass().getName());
            return new AddElementExecutor();
        } else {
            LOG.info("Checking {} ", nextPathResoverChain.getClass().getName());
            return nextPathResoverChain.resolve(path);
        }

    }
}
