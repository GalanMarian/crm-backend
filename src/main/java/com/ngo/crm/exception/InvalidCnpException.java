package com.ngo.crm.exception;

import com.ngo.crm.model.ErrorReason;

/**
 * Created by sgalan on 2/27/2023
 */

public class InvalidCnpException extends CrmException {
    public InvalidCnpException(String cnp) {
        super(ErrorReason.INVALID_CNP, cnp + " is invalid");
    }
}
