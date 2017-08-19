package com.cloudsea.forms.formservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.cloudsea.forms.formservice.question.repository.FormRepository;
import com.cloudsea.forms.formservice.question.service.FormsService;

@RunWith(MockitoJUnitRunner.class)
public class FormServiceTest {

	@Mock
	private FormRepository formRepository;

	@InjectMocks
	private FormsService formsService;

	@Test(expected = IllegalArgumentException.class)
	public void whenNullFormThorwIllegalArgumentExceptiom() {
		formsService.create(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void whenNullIdThrowIllegalArgumentException(){
		formsService.findById(null);
	}
	
}
