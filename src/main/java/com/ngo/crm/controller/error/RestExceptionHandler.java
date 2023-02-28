package com.ngo.crm.controller.error;

import com.ngo.crm.exception.CrmException;
import com.ngo.crm.exception.InvalidCnpException;
import com.ngo.crm.model.ErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by sgalan on 2/27/2023
 */

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


@ExceptionHandler({
        InvalidCnpException.class
})
 public ResponseEntity<ErrorMessage> invalidRequestParameterExceptionMapper(CrmException ex) {

 int statusCode = ex.getErrorReason().getHttpStatus().value();
 return ResponseEntity.status(statusCode).body(new ErrorMessage(statusCode,ex.getMessage()));
 }

}
