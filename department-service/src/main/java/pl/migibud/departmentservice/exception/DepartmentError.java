package pl.migibud.departmentservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DepartmentError {

    DEPARTMENT_NOT_FOUND,
    DEPARTMENT_WITH_CODE_ALREADY_EXISTS;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
