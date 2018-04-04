package com.cloudsea.forms.formservice.question.interpretor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateElementResolver implements PathResoverChain {

    private static final Logger LOG = LoggerFactory.getLogger(UpdateFormAttributeResolver.class);
    private PathResoverChain nextPathResoverChain;

    @Override
    public void nextResolver(PathResoverChain nextPathResoverChain) {
        this.nextPathResoverChain = nextPathResoverChain;
    }

    @Override
    public PatchExecutor resolve(String path) {

        if (path.contains("elements") && path.split("/").length > 2) {
            LOG.info("Returning {} ", getClass().getName());
            return new UpdateElementExecutor();
        } else {
            LOG.error("Cannot resolve path");
            throw new IllegalArgumentException("Path given for path request is not valid");
        }

    }
}
