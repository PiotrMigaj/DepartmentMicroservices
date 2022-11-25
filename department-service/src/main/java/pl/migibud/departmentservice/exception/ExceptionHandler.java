package pl.migibud.departmentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Collections;

@RestControllerAdvice
class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = DepartmentException.class)
    ResponseEntity<ErrorInfo> handleDepartmentException(DepartmentException e) {
        HttpStatus httpStatus = null;
        if (DepartmentError.DEPARTMENT_NOT_FOUND.equals(e.getDepartmentError())) {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        if (DepartmentError.DEPARTMENT_WITH_CODE_ALREADY_EXISTS.equals(e.getDepartmentError())) {
            httpStatus = HttpStatus.CONFLICT;
        }
        return ResponseEntity.status(httpStatus).body(
                new ErrorInfo(
                        httpStatus.value(),
                        Instant.now(),
                        Collections.singletonList(e.getDepartmentError().getMessage())
                )
        );
    }

}
