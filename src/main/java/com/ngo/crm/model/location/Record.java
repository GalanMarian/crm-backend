package com.ngo.crm.model.location;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Date;

/**
 * Created by sgalan on 2/28/2023
 */

@Getter
public class Record<FieldType>{
 @JsonProperty("id")
 private String id;
 @JsonProperty("createdTime")
 private Date createdTime;
 @JsonProperty("fields")
 private FieldType fields;
}
