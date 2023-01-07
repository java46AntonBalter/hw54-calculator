package telran.spring.calculator.controller;


import java.util.*;

import org.springframework.web.bind.annotation.*;


import telran.spring.calculator.dto.OperationData;
import telran.spring.calculator.service.Operation;

@RestController
@RequestMapping("calculator")
public class CalculatorController {
	Map<String, Operation> operationServices;

	public CalculatorController(Map<String, Operation> operationServices) {
		this.operationServices = operationServices;
	}
	@PostMapping
	String getOperationResult(@RequestBody OperationData data) {
		Operation operationService = operationServices.get(data.operationName);
		String res = operationService != null ? operationService.execute(data) : 
			String.format("Wrong operation name, should be one from %s",
					operationServices.keySet());
		return res;
			
	}
	@GetMapping
	Set<String> getAllOperationNames() {
		return operationServices.keySet();
	}
	

}
