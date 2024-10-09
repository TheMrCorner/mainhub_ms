package com.mrcorner.mainhub.utils;

import com.mrcorner.mainhub.exceptions.DataNotFoundException;
import com.mrcorner.mainhub.exceptions.InvalidDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class Http {

    private final RestTemplate restTemplate;

    public ResponseEntity<String> getRequest(String url) throws InvalidDataException, DataNotFoundException {
        HttpEntity<String> requestEntity = generateHttpEntity(null);
        try {
            return restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        } catch(HttpClientErrorException restClientException) {
            switch(restClientException.getStatusCode().value()){
                case 404:
                    throw new DataNotFoundException(restClientException.getMessage());
                default:
                    throw new InvalidDataException(restClientException.getMessage());
            }
        }
    }

    public ResponseEntity<String> postRequest(String url, String body){
        HttpEntity<String> requestEntity = generateHttpEntity(body);
        try {
            return restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        }
        catch(HttpClientErrorException httpClientErrorException){
            throw new InvalidDataException(httpClientErrorException.getMessage());
        }
    }

    public ResponseEntity<String> putRequest(String url, String body){
        HttpEntity<String> requestEntity = generateHttpEntity(body);
        try {
            return restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
        }
        catch(HttpClientErrorException httpClientErrorException){
            throw new InvalidDataException(httpClientErrorException.getMessage());
        }
    }

    private HttpEntity<String> generateHttpEntity(String body){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(body, headers);
    }

}
