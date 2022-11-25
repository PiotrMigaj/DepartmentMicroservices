package pl.migibud.employeeservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EmployeeError {

    EMPLOYEE_NOT_FOUND,
    EMPLOYEE_WITH_EMAIL_ALREADY_EXISTS;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
