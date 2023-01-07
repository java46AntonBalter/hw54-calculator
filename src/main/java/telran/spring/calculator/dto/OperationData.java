package telran.spring.calculator.dto;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@JsonTypeInfo(use=JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({@Type(ArithmeticOperationData.class),
	@Type(DateDaysOperationData.class), @Type(DatesOperationData.class)})
public class OperationData {
public String operationName;
public String additionalData;
}
