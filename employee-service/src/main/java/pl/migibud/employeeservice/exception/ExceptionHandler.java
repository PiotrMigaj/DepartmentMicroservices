package pl.migibud.employeeservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.util.Collections;

@RestControllerAdvice
class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = EmployeeException.class)
    ResponseEntity<ErrorInfo> handleEmployeeException(EmployeeException e) {
        HttpStatus httpStatus = null;
        if (EmployeeError.EMPLOYEE_NOT_FOUND.equals(e.getEmployeeError())) {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        if (EmployeeError.EMPLOYEE_WITH_EMAIL_ALREADY_EXISTS.equals(e.getEmployeeError())) {
            httpStatus = HttpStatus.CONFLICT;
        }
        return ResponseEntity.status(httpStatus).body(
                new ErrorInfo(
                        httpStatus.value(),
                        Instant.now(),
                        Collections.singletonList(e.getEmployeeError().getMessage())
                )
        );
    }

}
