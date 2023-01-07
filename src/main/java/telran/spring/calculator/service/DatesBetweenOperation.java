package telran.spring.calculator.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import telran.spring.calculator.dto.DatesOperationData;
import telran.spring.calculator.dto.OperationData;
@Service("dates-between")
public class DatesBetweenOperation implements Operation {

	@Override
	public String execute(OperationData data) {
		String res = "";
		DatesOperationData datesData = (DatesOperationData) data;
		try {
			LocalDate dateFrom = LocalDate.parse(datesData.dateFrom);
			LocalDate dateTo = LocalDate.parse(datesData.dateTo);
			res = ChronoUnit.DAYS.between(dateFrom, dateTo) + "";
		} catch (Exception e) {
			res = "Wrong Date Format should be YYYY-MM-DD";
		}
		return res;
	}

}
