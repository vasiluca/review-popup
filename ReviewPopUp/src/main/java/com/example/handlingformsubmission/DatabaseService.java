package com.example.handlingformsubmission;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;

import org.junit.jupiter.api.Assertions;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service
public class DatabaseService {
    private GeneralizedFeedbackData createGeneralized(Feedback feedback) {
        return new GeneralizedFeedbackData("", feedback.getContent());
    }

    private InputUserData createUserData(Feedback feedback) {
        InputUserData user = new InputUserData();
        user.name = feedback.getName();
        user.email = feedback.getEmail();

        return user;
    }

    private FeedbackData sendSubmitReq(GeneralizedFeedbackData gfb, RestTemplate restTemplate) {
        HttpEntity<GeneralizedFeedbackData> requestSubmit = new HttpEntity<>(gfb);

        String submitUrl = "http://localhost:8081/submit";
        ResponseEntity<FeedbackData> response = restTemplate
                .exchange(submitUrl, HttpMethod.POST, requestSubmit, FeedbackData.class);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode()); // Assertion will be removed later or moved to tests
        return response.getBody();
    }

    private UserData createUserReq(InputUserData userData, RestTemplate restTemplate) {
        HttpEntity<InputUserData> requestUser = new HttpEntity<>(userData);

        String usersCreateUrl = "http://localhost:8081/users/create";
        return restTemplate.postForObject(usersCreateUrl, requestUser, UserData.class);
    }

    private void notifyReviewPopUpService(long feedbackID, RestTemplate restTemplate) {
        String updateServiceUrl = "http://localhost:8084/notifyService/" + feedbackID; // this is the ReviewPopUp's Microservice URL
        restTemplate.exchange(updateServiceUrl, HttpMethod.POST, null, Void.class);
    }

    public void sendFeedback(Feedback feedback) {
        GeneralizedFeedbackData generalizedFeedback = createGeneralized(feedback);
        InputUserData userData = createUserData(feedback);

        RestTemplate restTemplate = new RestTemplate();


        long feedbackID = sendSubmitReq(generalizedFeedback, restTemplate).getId();
        feedback.setId(feedbackID); // this sets the ID for our internal Feedback Model for the message confirmation


        if (!userData.name.isEmpty() || !userData.email.isEmpty()) { // no user will be associated if no name and no email is provided
            long userID = createUserReq(userData, restTemplate).getId();

            String usersAssociateUrl = "http://localhost:8081/users/associate?feedback_id=" + feedbackID + "&user_id=" + userID;
            restTemplate.exchange(usersAssociateUrl, HttpMethod.PUT, null, Void.class);
        }

        notifyReviewPopUpService(feedbackID, restTemplate);
    }
}
