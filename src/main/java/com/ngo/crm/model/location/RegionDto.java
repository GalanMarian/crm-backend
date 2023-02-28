package com.ngo.crm.model.location;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sgalan on 2/28/2023
 */


@Getter
@Setter
public class RegionDto implements Serializable {

 @JsonProperty("Name")
 private String name;
 @JsonProperty("Cities")
 private List<String> cities;
}
