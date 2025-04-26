package com.example.handlingformsubmission;
import org.springframework.http.HttpMethod;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProviderService {
    public void notifyProvider(long feedbackID) {
        RestTemplate restTemplate = new RestTemplate();

        try {
            String updatedIdUrl = "http://localhost:8082/updated/" + feedbackID; // Actual Provider Microservice Url
            restTemplate.exchange(updatedIdUrl, HttpMethod.POST, null, Void.class);
        } catch (Exception e) {
            System.out.println("\nUnable to access the /updated/ endpoint from the Provider Microservice on Port 8082");
        }

    }
}
