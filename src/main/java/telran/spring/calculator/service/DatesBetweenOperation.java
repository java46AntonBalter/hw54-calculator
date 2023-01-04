package telran.spring.calculator.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import telran.spring.calculator.dto.DatesOperationData;
import telran.spring.calculator.dto.OperationData;

@Service("Days Between")
public class DatesBetweenOperation implements Operation {

	@Override
	public String execute(OperationData data) {
		DatesOperationData datesOperationData = (DatesOperationData) data;
		LocalDate date1 = LocalDate.parse(datesOperationData.dateFrom);
		LocalDate date2 = LocalDate.parse(datesOperationData.dateTo);
		return String.format("" + ChronoUnit.DAYS.between(date1, date2));
	}

}
