package telran.spring.calculator;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import telran.spring.calculator.controller.CalculatorController;
import telran.spring.calculator.dto.ArithmeticOperationData;

@WebMvcTest(CalculatorController.class)
class CalculatorControllerTest {
	@Autowired
	MockMvc mockMvc;
	ObjectMapper mapper = new ObjectMapper();

	@Test
	void rightDataCalculatorControllerTest() throws Exception {
		ArithmeticOperationData data = new ArithmeticOperationData();
		data.operationName = "arithmetic-simple";
		data.additionalData = "+";
		data.operand1 = 2.0;
		data.operand2 = 2.0;
		String requestJSON = mapper.writeValueAsString(data);
		mockMvc.perform(
				post("http://localhost:8080/calculator").contentType(MediaType.APPLICATION_JSON).content(requestJSON))
				.andExpect(status().isOk());

	}

	@Test
	void wrongDataCalculatorControllerTest() throws Exception {
		ArithmeticOperationData data = new ArithmeticOperationData();
		data.operationName = "simple";
		data.additionalData = "+";
		data.operand1 = 2.0;
		String requestJSON = mapper.writeValueAsString(data);
		mockMvc.perform(
				post("http://localhost:8080/calculator").contentType(MediaType.APPLICATION_JSON).content(requestJSON))
				.andExpect(status().isBadRequest());

	}

	@Test
	void getAllOperationNamesTest() throws Exception {
		MvcResult result = mockMvc.perform(get("http://localhost:8080/calculator")).andExpect(status().isOk())
				.andReturn();
		String res = result.toString();
	}

}
