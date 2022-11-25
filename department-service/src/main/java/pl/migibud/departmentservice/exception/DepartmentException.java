package pl.migibud.departmentservice.exception;

import lombok.Getter;

@Getter
public class DepartmentException extends RuntimeException{
	private DepartmentError departmentError;

	public DepartmentException(DepartmentError departmentError, String message) {
		this.departmentError = departmentError;
		this.departmentError.setMessage(message);
	}
}
