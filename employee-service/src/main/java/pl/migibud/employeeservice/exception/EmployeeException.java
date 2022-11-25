package pl.migibud.employeeservice.exception;

import lombok.Getter;

@Getter
public class EmployeeException extends RuntimeException{
	private EmployeeError employeeError;

	public EmployeeException(EmployeeError employeeError,String message) {
		this.employeeError = employeeError;
		this.employeeError.setMessage(message);
	}
}
