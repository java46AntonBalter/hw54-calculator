package telran.spring.calculator.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import telran.spring.calculator.dto.DateDaysOperationData;
import telran.spring.calculator.dto.OperationData;

@Service
public class DatesSimpleOperation implements Operation {
	@Value("${app.calculator.controller.wrong.opertaion.data: BadCastException}")
	String operationNameAndAdditionalDtoFieldsMismatchMessage;
	@Value("${app.calculator.DatesSimpleOperations.wrong.additionalData: additional data mismatch}")
	String additionalDataMismatchMessage;

	@Override
	public String execute(OperationData data) {
		List<String> additionalDataOptions = Arrays.asList(new String[] { "before", "after" });
		String res = "";
		try {
			DateDaysOperationData dateData = (DateDaysOperationData) data;
			LocalDate date = LocalDate.parse(dateData.date);
			int days = dateData.days;
			String beforeOrAfter = data.additionalData;

			if (!additionalDataOptions.contains(beforeOrAfter)) {
				return additionalDataMismatchMessage;
			}

			if (beforeOrAfter.equalsIgnoreCase("before")) {
				days = -days;
			}
			res = date.plusDays(days).toString();
		} catch (Exception e) {
			res = operationNameAndAdditionalDtoFieldsMismatchMessage;
		}

		return res;
	}

}
