package com.mwsgateway.apigateway.mail;

public interface FeedbackSender {
    void sendFeedback(String from, String name, String feedback);
}
