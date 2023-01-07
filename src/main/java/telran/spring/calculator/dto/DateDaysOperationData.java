package telran.spring.calculator.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class DateDaysOperationData extends OperationData {
	@NotNull
	@Pattern(regexp = "^((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$")

	public String date;
	@NotNull
	@Min(0)
	public int days;
}
