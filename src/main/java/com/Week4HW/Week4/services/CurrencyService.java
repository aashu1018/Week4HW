package com.Week4HW.Week4.services;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.json.JSONObject;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    @Value("${exchangeRatesService.API_KEY}")
    private String API_KEY;

    Logger log = LoggerFactory.getLogger(CurrencyService.class);

    private final RestClient getCurrencyServiceRestClient;

    public Double getConvertedCurrency(String baseCurrency, String currency, Double amount) {
        try{
            ResponseEntity<String> response = getCurrencyServiceRestClient
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .queryParam("apiKey",API_KEY)
                            .queryParam("base_currency",baseCurrency)
                            .queryParam("currencies",currency).build())
                    .retrieve()
                    .toEntity(String.class);
            JSONObject jsonObject=new JSONObject(response.getBody());
            JSONObject data=jsonObject.getJSONObject("data");
            log.info("JSON returned from the server {}", jsonObject);
            log.info("data in JSON {}", data);
            Map<String,Object> rates=data.toMap();
            String[] currencies = currency.split(",");
            return Double.parseDouble(rates.get(currencies[0]).toString())*amount;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
