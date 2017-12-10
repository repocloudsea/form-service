package com.cloudsea.forms.formservice.request.metadata;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/reqmeta")
public class RequestMetaDataController {

	private RequestMetadataRepository requestMetadataRepository;

	@Autowired
	public RequestMetaDataController(RequestMetadataRepository requestMetadataRepository) {
		this.requestMetadataRepository = requestMetadataRepository;
	}

	@GetMapping
	public List<RequestMetadata> getReqAllRequestMetatadata() {
		return requestMetadataRepository.findAll();
	}
}
