package com.cloudsea.forms.formservice.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudsea.forms.formservice.model.Form;
import com.cloudsea.forms.formservice.repository.FormRepository;

@Service
public class FormsService {

	private FormRepository formRepository;
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public FormsService(FormRepository formRepository) {
		this.formRepository = formRepository;
	}

	public void create(Form form) {
		LOGGER.debug(form.toString());
		formRepository.save(form);
	}

	public Form findById(String id) {
		return formRepository.findOne(id);
	}

	public List<Form> findAll() {
		return formRepository.findAll();
	}

	public List<Form> findByTitle(String title) {
		if (StringUtils.isBlank(title))
			throw new IllegalArgumentException("Title cannot be blank");
		return formRepository.findByTitle(title);
	}
}
