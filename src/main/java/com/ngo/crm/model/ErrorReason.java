package com.ngo.crm.model;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Created by sgalan on 2/27/2023
 */

public enum ErrorReason {
    INVALID_CNP(HttpStatus.BAD_REQUEST);

    @Getter
    private final HttpStatus httpStatus;

    ErrorReason(org.springframework.http.HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
