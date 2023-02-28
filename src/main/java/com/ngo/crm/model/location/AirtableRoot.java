package com.ngo.crm.model.location;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

/**
 * Created by sgalan on 2/28/2023
 */

@Getter
public class AirtableRoot<FieldType>{
 @JsonProperty("records")
 private List<Record<FieldType>> records;
}
