package telran.spring.calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import telran.spring.calculator.dto.*;
import telran.spring.calculator.service.*;

@SpringBootTest
class OperationsTests {
	@Autowired
	ArithmeticSimpleOperation arithmeticSimpleOperation;
	@Autowired
	DatesBetweenOperation datesBetweenOperation;
	@Autowired
	DatesSimpleOperation datesSimpleOperation;

	@Test
	void arithmeticSimpleOperationTest() {
		ArithmeticOperationData data = new ArithmeticOperationData();
		data.operationName = "arithmetic-simple";
		data.additionalData = "+";
		data.operand1 = 2.0;
		data.operand2 = 2.0;
		assertTrue(arithmeticSimpleOperation.execute(data).contains("4.0"));
	}

	@Test
	void arithmeticSimpleOperationWrongDtoTest() {
		DatesOperationData data = new DatesOperationData();
		data.operationName = "dates-between";
		data.dateFrom = "1989-12-04";
		data.dateTo = "2023-01-01";
		assertTrue(arithmeticSimpleOperation.execute(data).contains("mismatches"));
	}

}
