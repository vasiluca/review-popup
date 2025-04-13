package com.example.handlingformsubmission;

import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpEntity;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DatabaseService {
    public GeneralizedFeedbackData createGeneralized(Feedback feedback) {
        return new GeneralizedFeedbackData(null, feedback.getContent());
    }

    public void sendFeedback(Feedback feedback) {
        GeneralizedFeedbackData generalizedFeedback = createGeneralized(feedback);

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<GeneralizedFeedbackData> request = new HttpEntity<>(generalizedFeedback);
        String submitUrl = "http://localhost:8081/submit";
        GeneralizedFeedbackData gfb = restTemplate.postForObject(submitUrl, request, GeneralizedFeedbackData.class);

        Assertions.assertNotNull(gfb);
        Assertions.assertEquals(gfb.getContent(), feedback.getContent());
    }
}
