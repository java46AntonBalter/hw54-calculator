package telran.spring.calculator.service;

import java.util.*;
import java.util.function.BiFunction;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import telran.spring.calculator.dto.*;

@Service
public class ArithmeticSimpleOperation implements Operation {
	private static Map<String, BiFunction<Double, Double, String>> operations;
	@Value("${app,calculator.controller.wrong.opertaion.data: BadCastException}")
	String operationNameAndAdditionalDtoFieldsMismatchMessage;
	@Value("${app.calculator.ArithmeticSimpleOperation.wrong.operation: Wrong arithmetic operation}")
	String wrongArithmeticOperationMessage;

	static {
		operations = new HashMap<>();
		operations.put("*", (o1, o2) -> o1 * o2 + "");
		operations.put("-", (o1, o2) -> o1 - o2 + "");
		operations.put("+", (o1, o2) -> o1 + o2 + "");
		operations.put("/", (o1, o2) -> o1 / o2 + "");

	}

	@Override
	public String execute(OperationData data) {

		ArithmeticOperationData arithmeticData;
		String res = null;
		try {
			arithmeticData = (ArithmeticOperationData) data;
			var function = operations.getOrDefault(data.additionalData, (o1, o2) -> wrongArithmeticOperationMessage);
			res = function.apply(arithmeticData.operand1, arithmeticData.operand2);
		} catch (Exception e) {
			res = operationNameAndAdditionalDtoFieldsMismatchMessage;
		}
		return res;
	}

}
