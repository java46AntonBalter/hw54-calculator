package telran.spring.calculator.controller;

import java.util.Map;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import telran.spring.calculator.service.Operation;
import telran.spring.calculator.dto.*;

@RestController
@RequestMapping("calculator")
public class CalculatorController {

	Map<String, Operation> operations;

	public CalculatorController(Map<String, Operation> operations) {
		this.operations = operations;
	}

	@PostMapping
	String performOperation(@RequestBody OperationData operationData) {
		Operation operation = operations.get(operationData.operationName);
		return operation != null ? operation.execute(operationData) : "Wrong name " + operationData.operationName;
	}

	@GetMapping
	Set<String> getTypes() {
		return operations.keySet();
	}

}
