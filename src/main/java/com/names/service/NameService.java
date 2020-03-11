package com.names.service;

import com.names.db.DataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.ConnectException;

@Service
public class NameService {

    @Autowired
    DataStore dataStore;

    private RestTemplate template = new RestTemplate();

    @Retryable(maxAttempts = 5, value = ConnectException.class,
            backoff = @Backoff(delay = 1000, multiplier = 2))
    public Integer getNewId() throws ConnectException {
        System.out.println("Trying...");

        return template.exchange("http://localhost:8082/", HttpMethod.GET, null, Integer.class).getBody();
    }

    @Recover
    private void recoveryMethodTBD() {
        //TODO Figure out what to do
    }
}