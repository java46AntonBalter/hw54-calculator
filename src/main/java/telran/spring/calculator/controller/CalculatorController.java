package telran.spring.calculator.controller;

import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	static Logger LOG = LoggerFactory.getLogger(CalculatorController.class);
	List<Operation> operations;
	Map<String, Operation> operationServices;
	@Value("${app.message.wrong.operation.name}")
	String wrongOperationMessage;

	public CalculatorController(List<Operation> operations) {
		this.operations = operations;
	}

	@PostMapping
	String getOperationResult(@RequestBody @Valid OperationData data) {
		LOG.debug("recieved request for calculator service:{}, additional data: {}", data.operationName,
				data.additionalData);
		Operation operationService = operationServices.get(data.operationName);
		String res;
		if (operationService != null) {
			res = operationService.execute(data);
		} else {
			LOG.error(wrongOperationMessage + "{}", operationServices.keySet());
			res = String.format(wrongOperationMessage + " %s", operationServices.keySet());
		}
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
		LOG.info("application context is created with services {}", operationServices.keySet());
	}

	@PreDestroy
	void shutdown() {
		LOG.info("shutdown performed");
	}

}
