package com.cloudsea.forms.formservice;

import java.util.List;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cloudsea.forms.formservice.question.model.Form;
import com.cloudsea.forms.formservice.question.repository.FormRepository;

@RunWith(SpringRunner.class)
@DataMongoTest
public class FormserviceRepositoryTest {

	@Autowired
	private FormRepository formRepository;

	@Test
	public void whenOneElemetPresent_SizeShouldBeOne() {
		formRepository.save(new Form());
		List<Form> forms = formRepository.findAll();
		Assertions.assertThat(forms.size() == 1);
	}

	@Test
	public void whenOneFormAdded_FindByIdShouldReturnOne() {
		UUID uuid = UUID.randomUUID();

		Form form = new Form();
		form.setId(uuid.toString());

		formRepository.save(form);
		Form dBform = formRepository.findOne(uuid.toString());
		Assertions.assertThat(dBform != null);
	}

	@Test
	public void whenTwoFormAddedWithSameTitle_FindByTitleShouldReturnTwo() {
		UUID uuid1 = UUID.randomUUID();
		UUID uuid2 = UUID.randomUUID();

		Form form1 = new Form();
		form1.setId(uuid1.toString());
		form1.setTitle("FormTitle1");

		Form form2 = new Form();
		form2.setId(uuid2.toString());
		form2.setTitle("FormTitle1");

		formRepository.save(form1);
		formRepository.save(form2);
		List<Form> forms = formRepository.findByTitle("FormTitle1");
		Assertions.assertThat(forms.size() == 2);
	}

}
