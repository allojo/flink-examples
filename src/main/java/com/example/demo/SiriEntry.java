package com.example.demo;


import java.sql.Timestamp;

public class SiriEntry {
  private   String userId;
  private  String topic;
  private Timestamp timestamp;

    public String getTopic() {
        return topic;
    }

    public SiriEntry() {
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public SiriEntry(String userId, String topic, Timestamp timestamp) {
        this.userId = userId;
        this.topic = topic;
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }



    @Override
    public String toString() {
        return getTopic();
    }
}
