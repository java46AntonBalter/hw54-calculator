package telran.spring.calculator.service;

import telran.spring.calculator.dto.DateDaysOperationData;
import telran.spring.calculator.dto.OperationData;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

@Service("Date Operations")
public class DatesSimpleOperation implements Operation {

	@Override
	public String execute(OperationData data) {
		DateDaysOperationData dateDaysOperationData = (DateDaysOperationData) data;
		if (dateDaysOperationData.additionalData.equals(null)) {
			return String.format("please provide additional data - operation type (+, -)");
		}
		LocalDate date = LocalDate.parse(dateDaysOperationData.date);
		int days = dateDaysOperationData.days;
		if (dateDaysOperationData.additionalData.equals("+")) {
			return String.format("%s", date.plusDays(days).toString());
		}
		if (dateDaysOperationData.additionalData.equals("-")) {
			return String.format("%s", date.minusDays(days).toString());
		}
		return String.format("please provide correct data");

	}

}
