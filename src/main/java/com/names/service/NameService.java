package com.names.service;

import com.names.db.DataStore;
import com.names.exception.NoConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.ConnectException;
import java.net.http.HttpClient;

@Service
@EnableRetry
public class NameService {

    @Autowired
    private DataStore dataStore;

    private static final Logger logger = LoggerFactory.getLogger(HttpClient.class);

    private RestTemplate template = new RestTemplate();

    @Retryable(maxAttemptsExpression = "${retry.service.retry-attempts}", value = {ConnectException.class}, backoff = @Backoff(delayExpression = "${retry.service.retry-delay}"))
    public Integer getNewId() throws NoConnectionException {
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