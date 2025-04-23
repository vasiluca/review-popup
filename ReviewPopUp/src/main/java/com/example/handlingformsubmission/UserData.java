package com.example.handlingformsubmission;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String name;
    String email;
    @OneToMany(mappedBy = "author")
    List<FeedbackData> feedbackData;

    public UserData(String name, String email) {
        this.name = name;
        this.email = email;
    }
    public UserData() {}

    public long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public List<FeedbackData> getFeedbackData() { return feedbackData; }
}
