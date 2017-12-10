package com.cloudsea.forms.formservice.question.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cloudsea.forms.formservice.question.model.Form;

public interface FormRepository extends MongoRepository<Form, String> {

	List<Form> findByTitle(String title);

	List<Form> findByUserId(String userId);

}
