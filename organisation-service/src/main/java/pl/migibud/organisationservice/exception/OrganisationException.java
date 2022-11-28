package pl.migibud.organisationservice.exception;

import lombok.Getter;

@Getter
public class OrganisationException extends RuntimeException{
	private OrganisationError organisationError;

	public OrganisationException(OrganisationError organisationError, String message) {
		this.organisationError = organisationError;
		this.organisationError.setMessage(message);
	}
}
