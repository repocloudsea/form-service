package com.cloudsea.forms.formservice.answers.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cloudsea.forms.formservice.answers.model.Result;

public interface ResultsRepository extends MongoRepository<Result, String> {

	List<Result> findByFormId(String id);
}
