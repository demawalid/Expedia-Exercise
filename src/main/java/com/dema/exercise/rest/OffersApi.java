package com.dema.exercise.rest;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



@Component
public class OffersApi {

    private final static String OFFERS_URL = "https://offersvc.expedia.com/offers/v2/getOffers?scenario=deal-finder&page=foo&uid=foo&productType=Hotel";
    private RestTemplate template = new RestTemplate();

    public LinkedHashMap findOffers(Map<String, String> offerParameters) {
        StringBuilder url = new StringBuilder(OFFERS_URL);
        for (String key : offerParameters.keySet()) {
            url.append(String.format("&%s=%s", key, offerParameters.get(key)));
        }
        ResponseEntity<LinkedHashMap> offersEntity = template.getForEntity(url.toString(), LinkedHashMap.class);
        if (offersEntity.getStatusCode() == HttpStatus.OK) {
            return offersEntity.getBody();
        }
        return null;

    }

}
