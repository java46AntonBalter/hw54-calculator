package telran.spring.calculator.controller;


import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import telran.spring.calculator.dto.OperationData;
import telran.spring.calculator.service.Operation;

@RestController
@RequestMapping("calculator")
public class CalculatorController {
	List<Operation> operations;
	Map<String, Operation> operationServices;
	@Value("${app.message.wrong.operation.name}")
	String wrongOperationMessage;

	public CalculatorController(List<Operation> operations) {
		this.operations = operations;
	}
	@PostMapping
	String getOperationResult(@RequestBody @Valid OperationData data) {
		Operation operationService = operationServices.get(data.operationName);
		String res = operationService != null ? operationService.execute(data) : 
			String.format(wrongOperationMessage + " %s",
					operationServices.keySet());
		return res;
			
	}
	@GetMapping
	Set<String> getAllOperationNames() {
		return operationServices.keySet();
	}
	@PostConstruct
	void createMapOperationsServices() {
		operationServices = operations.stream()
				.collect(Collectors.toMap(Operation::getOperationName, service -> service));
	}
	

}
