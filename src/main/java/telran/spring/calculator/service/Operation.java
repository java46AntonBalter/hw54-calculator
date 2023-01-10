package telran.spring.calculator.service;

import org.springframework.beans.factory.annotation.Value;

import telran.spring.calculator.dto.OperationData;

public interface Operation {
String execute(OperationData data);
String getOperationName();
@Value("${app.message.wrong.dto.fileds}")
String wrongDtoMessage = "";
}
