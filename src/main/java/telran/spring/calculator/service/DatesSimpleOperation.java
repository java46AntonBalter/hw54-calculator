package telran.spring.calculator.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import telran.spring.calculator.dto.DateDaysOperationData;
import telran.spring.calculator.dto.OperationData;
@Service("dates-simple")
public class DatesSimpleOperation implements Operation {

	@Override
	public String execute(OperationData data) {
		String res = "";
		DateDaysOperationData dateData = (DateDaysOperationData) data;
		try {
			LocalDate date = LocalDate.parse(dateData.date);
			int days = dateData.days;
			if(data.additionalData.equalsIgnoreCase("before")) {
				days = -days;
			}
			res = date.plusDays(days).toString();
		} catch (Exception e) {
			res = "Wrong Date format should be YYYY-MM-DD";
		} 
		
		
		
		return res;
	}

}
