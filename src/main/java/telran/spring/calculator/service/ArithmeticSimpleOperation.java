package telran.spring.calculator.service;

import java.util.*;
import java.util.function.BiFunction;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import telran.spring.calculator.dto.*;
@Service
public class ArithmeticSimpleOperation implements Operation {
private static Map<String, BiFunction<Double, Double, String>> operations;
@Value("${app.message.wrong.operation.arithmetic}")
String wrongOperation;

static {
	operations = new HashMap<>();
	operations.put("*", (o1, o2) -> o1 * o2 + "");
	operations.put("-", (o1, o2) -> o1 - o2 + "");
	operations.put("+", (o1, o2) -> o1 + o2 + "");
	operations.put("/", (o1, o2) -> o1 / o2 + "");
	
}
	@Override
	public String execute(OperationData data) {
		String res = "";
		try {
			ArithmeticOperationData arithmeticData = (ArithmeticOperationData) data;
			var function = operations.getOrDefault(data.additionalData,
					(o1, o2) -> wrongOperation + " (*,/,+,-)");
			res =  function.apply(arithmeticData.operand1, arithmeticData.operand2);
		} catch (ClassCastException e) {
			res = wrongDtoMessage;
		}
		return res;
	}
	@Override
	public String getOperationName() {
		return "arithmetic-simple";
	}
	

}
