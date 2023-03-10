package telran.spring.calculator.dto;

import jakarta.validation.constraints.*;

public class DateDaysOperationData extends OperationData {
	@Pattern(regexp = OperationData.DATE_PATTERN)
	@NotNull
	public String date;
	@Positive
	public int days;
}
