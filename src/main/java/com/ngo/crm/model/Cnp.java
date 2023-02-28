package com.ngo.crm.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Created by sgalan on 2/27/2023
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cnp {

 private String CNP;

 private String gender;

 private LocalDate dateOfBirth;
}
