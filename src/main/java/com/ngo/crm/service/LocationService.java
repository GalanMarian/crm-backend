package com.ngo.crm.service;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ngo.crm.model.location.RegionDto;
import com.ngo.crm.service.airtable.AirtableClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sgalan on 2/28/2023
 */

@Service
public class LocationService {
  private final AirtableClient airtableClient;

  private final ObjectMapper objectMapper;


 public LocationService(AirtableClient airtableClient, ObjectMapper objectMapper) {
  this.airtableClient = airtableClient;

     this.objectMapper = objectMapper;
 }

 public List<RegionDto> retrieveRegionsAndCities(){
  var regions = airtableClient.retrieveRegions();
  return regions.getRecords().stream().map(regionRecord -> objectMapper.convertValue(regionRecord.getFields(), RegionDto.class)).collect(Collectors.toList());
 }


}
