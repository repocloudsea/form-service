package com.cloudsea.forms.formservice;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.cloudsea.forms.formservice.question.controller.FormConfigureController;
import com.cloudsea.forms.formservice.question.service.FormsService;

@RunWith(SpringRunner.class)
@WebMvcTest(FormConfigureController.class)
public class FormConfigureControllerTest {

	@Autowired
	private MockMvc mockmvc;
	
	@MockBean
	private FormsService formsService;
	
	@Test
	public void createFormAndShouldReturnTheCreatedForm() throws Exception{
		mockmvc.perform(post("/forms/create")).andDo(print()).andExpect(status().isCreated()).
		andExpect(content().json("formId")).andDo(document("home"));
		
	}

}
