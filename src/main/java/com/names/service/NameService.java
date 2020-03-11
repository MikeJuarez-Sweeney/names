package com.names.service;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NameService {

    private String url = "http://localhost:8082/";
    private RestTemplate template = new RestTemplate();

    public Integer getNewId() {

        return template.exchange(url, HttpMethod.GET, null, Integer.class).getBody();
    }

}
