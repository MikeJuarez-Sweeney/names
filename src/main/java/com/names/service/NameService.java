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
    private DataStore dataStore;

    private RestTemplate template = new RestTemplate();

    @Retryable(maxAttempts = 3, value = ConnectException.class,
            backoff = @Backoff(delay = 1000, multiplier = 2))
    public Integer getNewId() throws ConnectException {
        System.out.println("Trying...");
        Integer id = template.exchange("http://localhost:8082/", HttpMethod.GET, null, Integer.class).getBody();
        if (dataStore.checkKey(id))
            id = getNewId();
        return id;
    }

    @Recover
    public Integer createId() {
        int id = dataStore.createId();
        if (dataStore.checkKey(id))
            id = createId();
        return id;
    }
}