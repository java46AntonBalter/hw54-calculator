package telran.spring.calculator.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import telran.spring.calculator.dto.DatesOperationData;
import telran.spring.calculator.dto.OperationData;

@Service
public class DatesBetweenOperation implements Operation {

	@Override
	public String execute(OperationData data) {
		String res = "";
		try {
			DatesOperationData datesData = (DatesOperationData) data;

			LocalDate dateFrom = LocalDate.parse(datesData.dateFrom);
			LocalDate dateTo = LocalDate.parse(datesData.dateTo);
			res = ChronoUnit.DAYS.between(dateFrom, dateTo) + "";
			LOG.debug("executed operation: {}, with parameters: date from :{}, date to:{}, result:{}",
					getOperationName(), datesData.dateFrom, datesData.dateTo, res);
		} catch (ClassCastException e) {
			res = wrongDtoMessage;
			LOG.error("Exception {}", e.getMessage());
		}
		return res;
	}

	@Override
	public String getOperationName() {

		return "dates-between";
	}

}
