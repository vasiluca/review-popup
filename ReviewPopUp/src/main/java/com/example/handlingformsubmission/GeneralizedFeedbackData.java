package com.example.handlingformsubmission;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * Feedback data with Jackson annotations and no ID in order to preserve wanted behavior
 * at each functional layer.
 */
public class GeneralizedFeedbackData {
    @JsonProperty("product_name")
    public String productName;

    @JsonProperty("content")
    public String content;

    @JsonProperty("metadata")
    public Map<String, String> metaData;

    public GeneralizedFeedbackData() {}
    public GeneralizedFeedbackData(String productName, String content) {
        this.productName = productName;
        this.content = content;
        this.metaData = new HashMap<>();
    }

    public String getProductName() {
        return productName;
    }

    public String getContent() {
        return content;
    }
}
