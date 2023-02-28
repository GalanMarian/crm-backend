package com.ngo.crm.model.location;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by sgalan on 2/28/2023
 */


@Setter
@Getter
public class Region {
    @JsonProperty("Id")
    private Integer id;
    @JsonProperty("Name")
    private String name;
    @JsonIgnore
    @JsonProperty("City")
    private transient String citiesIds;
    @JsonProperty("Cities")
    private List<String> cities;
}
