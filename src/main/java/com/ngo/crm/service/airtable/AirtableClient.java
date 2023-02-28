package com.ngo.crm.service.airtable;

import com.ngo.crm.model.location.AirtableRoot;
import com.ngo.crm.model.location.Region;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Created by sgalan on 2/28/2023
 */

@Service
public class AirtableClient {
 private final WebClient webClient;


 public AirtableClient(WebClient airtableWebClient) {

  this.webClient = airtableWebClient;
 }

 public AirtableRoot<Region> retrieveRegions(){
    var w =  webClient
            .post()
            .uri("/Region/listRecords")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(AirtableRoot.class)
            .block();
    return w;
 }


}
