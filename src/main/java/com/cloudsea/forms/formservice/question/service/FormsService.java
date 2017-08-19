package com.cloudsea.forms.formservice.question.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cloudsea.forms.formservice.question.repository.FormRepository;
import com.cloudsea.forms.formservice.questions.model.Element;
import com.cloudsea.forms.formservice.questions.model.Form;

@Service
public class FormsService {

	private FormRepository formRepository;

	@Autowired
	public FormsService(FormRepository formRepository) {
		this.formRepository = formRepository;
	}

	public void create(Form form) {
		if (form == null)
			throw new IllegalArgumentException("Form cannot be null");

		formRepository.save(form);
	}

	public Form findById(String id) {
		if (StringUtils.isBlank(id))
			throw new IllegalArgumentException("ID Cannot be null or blank");
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