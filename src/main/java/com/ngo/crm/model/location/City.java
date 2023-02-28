package com.ngo.crm.model.location;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by sgalan on 2/28/2023
 */

@Data
@AllArgsConstructor
public class City {

 private Integer id;
 private String name;
 private Integer regionId;
}
