package telran.spring.calculator.service;

import org.springframework.stereotype.Service;

import telran.spring.calculator.dto.ArithmeticOperationData;
import telran.spring.calculator.dto.OperationData;

@Service("Arithmetic Operations")
public class ArithmeticSimpleOperation implements Operation {

	@Override
	public String execute(OperationData data) {
		ArithmeticOperationData arithmeticOperationData = (ArithmeticOperationData) data;
		if (arithmeticOperationData.additionalData.equals(null)) {
			return String.format("please provide additional data - operation type (+, -, *, /)");
		}
		double a = arithmeticOperationData.operand1;
		double b = arithmeticOperationData.operand2;
		String action = arithmeticOperationData.additionalData;
		if (action.equals("+")) {
			return String.format("%.2f", a + b);
		}
		if (action.equals("-")) {
			return String.format("%.2f", a - b);
		}
		if (action.equals("*")) {
			return String.format("%.2f", a * b);
		}
		if (action.equals("/")) {
			return String.format("%.2f", a / b);
		}
		return String.format("please provide correct data");

	}

}
