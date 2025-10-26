package com.example.news.controller.dto.response;

import java.time.LocalDateTime;

public class ErrorResponse {
    private String message;
    private String details;
    private int status;
    private String timestamp;

    public ErrorResponse(String message, String details, int status) {
        this.message = message;
        this.details = details;
        this.status = status;
        this.timestamp = LocalDateTime.now().toString();
    }

    // Getters and setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}