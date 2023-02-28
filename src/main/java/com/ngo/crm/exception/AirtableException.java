package com.ngo.crm.exception;

import lombok.Getter;

import java.util.Optional;

/**
 * Created by sgalan on 2/28/2023
 */

public class AirtableException extends RuntimeException {
    @Getter
    private final Optional<Error> errorResponse;


    public AirtableException(String message) {
        super(message);
        errorResponse = Optional.empty();
    }

    public AirtableException(String message, Throwable cause) {
        super(message, cause);
        errorResponse = Optional.empty();
    }

    public AirtableException(Error errorResponse) {
        super("Airtable error occurred");
        this.errorResponse = Optional.of(errorResponse);
    }
}
