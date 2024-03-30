package com.mrcorner.mainhub.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class Http {

    private final RestTemplate restTemplate;

    public ResponseEntity<String> getRequest(String url){
        HttpEntity<String> requestEntity = generateHttpEntity(null);
        return restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
    }

    public ResponseEntity<String> postRequest(String url, String body){
        HttpEntity<String> requestEntity = generateHttpEntity(body);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
    }

    public ResponseEntity<String> putRequest(String url, String body){
        HttpEntity<String> requestEntity = generateHttpEntity(body);
        return restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
    }

    private HttpEntity<String> generateHttpEntity(String body){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(body, headers);
    }

}
