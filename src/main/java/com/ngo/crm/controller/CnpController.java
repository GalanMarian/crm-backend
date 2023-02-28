package com.ngo.crm.controller;

import com.ngo.crm.model.Cnp;
import com.ngo.crm.service.CnpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sgalan on 2/27/2023
 */

@RestController
@RequestMapping("/api")
public class CnpController {


    private final CnpService cnpService;

    public CnpController(CnpService cnpService) {
        this.cnpService = cnpService;
    }

    @GetMapping("/cnp")
    public ResponseEntity<Cnp> retrieveCnp(@RequestParam String cnp){

        return ResponseEntity.ok().body(cnpService.validateAndRetrieveCnpData(cnp));
    }
}
