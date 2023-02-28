package com.ngo.crm.exception;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * Created by sgalan on 2/28/2023
 */

@Data
@ToString
public class Error {

 private String type;
 private String title;
 private int status;
 private String detail;
 private String instance;
 private Date timestamp;

}
