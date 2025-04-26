package com.example.handlingformsubmission;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClientService {
    public FeedbackData getById(long feedbackID) {
        RestTemplate restTemplate = new RestTemplate();
        // communicate with database to get information for specific feedback ID
        String getIdUrl = "http://localhost:8081/id?id=" + feedbackID;
        return restTemplate.getForObject(getIdUrl, FeedbackData.class); // the returned Object will be used by the Client
    }
}
