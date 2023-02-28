package com.ngo.crm.exception;

import com.ngo.crm.model.ErrorReason;
import lombok.Getter;


@Getter
public class CrmException extends RuntimeException {
    private final ErrorReason errorReason;
    private final String message;

    public CrmException(ErrorReason errorReason, String message) {
        super(message);
        this.errorReason = errorReason;
        this.message = message;
    }
}
