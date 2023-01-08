package telran.spring.calculator.controller;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.validation.Valid;
import telran.spring.calculator.dto.OperationData;
import telran.spring.calculator.service.Operation;

@RestController
@RequestMapping("calculator")
public class CalculatorController {
	@Autowired
	Map<String, Operation> operationServices;
	List<Operation> operationsList;
	@Value("${app.calculator.wrong.operation.name:Wrong Name}")
	String wrongOperationNameMessage;

	public CalculatorController(List<Operation> operationsList) {
		this.operationsList = operationsList;
	}

	@PostMapping
	String getOperationResult(@RequestBody @Valid OperationData data) {
		Operation operationService = operationServices.get(data.operationName);
		String res = operationService != null ? operationService.execute(data)
				: String.format("%s(%s). Should be one of the following options -  %s", wrongOperationNameMessage,
						data.operationName, operationServices.keySet());
		return res;

	}

	@GetMapping
	Set<String> getAllOperationNames() {
		return operationServices.keySet();
	}

	@PostConstruct
	void getOperationsMap() {
		operationServices = operationsList.stream()
				.collect(Collectors.toMap(o -> o.getClass().getSimpleName(), o -> o));

	}

	@PreDestroy
	void shutdown() {
	}
}
