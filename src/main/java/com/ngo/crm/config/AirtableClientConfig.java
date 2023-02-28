package com.ngo.crm.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ngo.crm.exception.AirtableException;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

/**
 * Created by sgalan on 2/28/2023
 */

@Slf4j
@Setter
@Configuration
public class AirtableClientConfig {
    @Value("${airtable.token}")
    private String authToken;

    @Value("${airtable.url}")
    private String url;

    private final ObjectMapper objectMapper = Jackson2ObjectMapperBuilder
            .json()
            .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .featuresToDisable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .featuresToDisable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
            .modules(new JavaTimeModule())
            .build();

    @Bean
    public WebClient airtableWebClient(WebClient.Builder webClientBuilder) {

        var strategies = ExchangeStrategies
                .builder()
                .codecs(clientDefaultCodecsConfigurer -> {
                    clientDefaultCodecsConfigurer.customCodecs().register(new Jackson2JsonEncoder(objectMapper));
                    clientDefaultCodecsConfigurer.customCodecs().register(new Jackson2JsonDecoder(objectMapper));
                }).build();

        return webClientBuilder
                .filter(airtableErrorResponseHandler())
                .exchangeStrategies(strategies)
                .defaultHeaders(header -> {
                    header.setBearerAuth(authToken);
                    header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                })
                .baseUrl(url)
                .build();
    }


    private ExchangeFilterFunction airtableErrorResponseHandler() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            if (clientResponse.statusCode().isError()) {
                var contentLength = clientResponse.headers().contentLength();
                var contentType = clientResponse.headers().contentType();
                if (contentLength.isPresent() && contentLength.getAsLong() == 0 ||
                        contentType.isPresent() && !contentType.get().isCompatibleWith(MediaType.APPLICATION_JSON)) {
                    log.info("Airtable error response [{}]", clientResponse.statusCode());
                    return Mono.error(new AirtableException(String.format("Airtable error response [status=%s]",
                            clientResponse.statusCode())));
                }
            }
            return Mono.just(clientResponse);
        });
    }

}
