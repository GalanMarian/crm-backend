package com.ngo.crm.controller.location;

import com.ngo.crm.model.location.Record;
import com.ngo.crm.model.location.Region;
import com.ngo.crm.model.location.RegionDto;
import com.ngo.crm.service.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by sgalan on 2/28/2023
 */

@RestController
@RequestMapping("/api")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }


    @GetMapping(value = "/regions")
    public ResponseEntity<List<RegionDto>> retrieveRegions(){
        return ResponseEntity.ok().body(locationService.retrieveRegionsAndCities());

    }
}
