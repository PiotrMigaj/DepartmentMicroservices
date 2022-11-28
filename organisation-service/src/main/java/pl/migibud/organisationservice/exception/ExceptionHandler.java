package pl.migibud.organisationservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Collections;

@RestControllerAdvice
class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = OrganisationException.class)
    ResponseEntity<ErrorInfo> handleEmployeeException(OrganisationException e) {
        HttpStatus httpStatus = null;
        if (OrganisationError.ORGANISATION_NOT_FOUND.equals(e.getOrganisationError())) {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return ResponseEntity.status(httpStatus).body(
                new ErrorInfo(
                        httpStatus.value(),
                        Instant.now(),
                        Collections.singletonList(e.getOrganisationError().getMessage())
                )
        );
    }

}
