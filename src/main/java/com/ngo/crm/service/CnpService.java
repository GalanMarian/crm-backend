package com.ngo.crm.service;

import com.ngo.crm.exception.InvalidCnpException;
import com.ngo.crm.model.Cnp;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.regex.*;

import java.time.LocalDate;
import java.time.Month;

/**
 * Created by sgalan on 2/27/2023
 */

@Service
@RequiredArgsConstructor
public class CnpService {

    public Cnp validateAndRetrieveCnpData(String cnp){

        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("^[1-9]\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])(0[1-9]|[1-4]\\d|5[0-2]|99)(00[1-9]|0[1-9]\\d|[1-9]\\d\\d)\\d$");
        Matcher matcher = pattern.matcher(cnp);
        if (!matcher.matches())
            throw new InvalidCnpException(cnp);

        String year = cnp.substring(1, 3);
        String month = cnp.substring(3, 5);
        String day = cnp.substring(5, 7);

        int genderDigit = Integer.parseInt(cnp.substring(0, 1));
        year = switch (genderDigit) {
            case 1, 2 -> "19" + year;
            case 3, 4 -> "18" + year;
            default -> "20" + year;
        };
        String gender = genderDigit % 2 == 0 ? "female" : "male";
        return Cnp.builder()
                .CNP(cnp)
                .dateOfBirth(LocalDate.of(Integer.parseInt(year), Month.of(Integer.parseInt(month)),Integer.parseInt(day)))
                .gender(gender)
                .build();

    }
}
