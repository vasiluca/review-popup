package com.example.handlingformsubmission;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

/**
 * "Specified" FeedbackData class that adds an id. Can't extend GeneralizedFeedbackData
 * because I needed to annotate metaData with @ElementCollection to make it work with JPA.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class FeedbackData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @JsonProperty("product_name")
    String productName;
    String content;
    @JsonProperty("metadata")
    @ElementCollection
    Map<String,String> metaData;

    @JsonIncludeProperties("id")
    @JsonProperty("user_data")
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    UserData author;

    public FeedbackData(String pn, String c, Map<String,String> md) {
        //this.id = id;
        productName = pn;
        content = c;
        metaData = md;

    }
}
