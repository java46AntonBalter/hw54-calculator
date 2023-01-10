package telran.spring.calculator.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import telran.spring.calculator.dto.DateDaysOperationData;
import telran.spring.calculator.dto.OperationData;

@Service
public class DatesSimpleOperation implements Operation {

	@Override
	public String execute(OperationData data) {
		String res = "";
		try {
			DateDaysOperationData dateData = (DateDaysOperationData) data;

			LocalDate date = LocalDate.parse(dateData.date);
			int days = dateData.days;
			if (data.additionalData.equalsIgnoreCase("before")) {
				days = -days;
			}
			res = date.plusDays(days).toString();
			LOG.debug("executed operation: {}, with parameters: additional data: {}, date: {}, days:{}, result:{}",
					getOperationName(), dateData.additionalData, dateData.date, dateData.days, res);
		} catch (ClassCastException e) {
			res = wrongDtoMessage;
			LOG.error("Exception {}", e.getMessage());
		}

		return res;
	}

	@Override
	public String getOperationName() {

		return "dates-simple";
	}

}
