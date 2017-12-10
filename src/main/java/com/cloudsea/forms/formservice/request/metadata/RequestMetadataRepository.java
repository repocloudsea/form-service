package com.cloudsea.forms.formservice.request.metadata;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RequestMetadataRepository extends MongoRepository<RequestMetadata, String> {

}
