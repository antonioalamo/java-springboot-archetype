package com.example.news.service.exception;

public class NewsCreationException extends RuntimeException {
    public NewsCreationException(String message) {
        super(message);
    }
}