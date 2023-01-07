package telran.spring.calculator.dto;

import jakarta.validation.constraints.NotNull;

public class ArithmeticOperationData extends OperationData {
	@NotNull
	public double operand1;
	@NotNull
	public double operand2;
}
