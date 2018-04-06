package com.cloudsea.forms.formservice.question.interpretor;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResolverConfig {

    @Bean
    public PathResoverChain getPathResolverChain() {

        PathResoverChain removeElementResolver = new RemoveElementResolver();
        PathResoverChain updateFormResolver = new UpdateFormAttributeResolver();
        PathResoverChain addElementResolver = new AddElementResolver();
        PathResoverChain udpateElementResolver = new UpdateElementResolver();

        removeElementResolver.nextResolver(addElementResolver);
        addElementResolver.nextResolver(updateFormResolver);
        updateFormResolver.nextResolver(udpateElementResolver);

        return removeElementResolver;


    }
}
