package com.example.news.service.exception;

public class  NewsValidationException extends RuntimeException {
    public NewsValidationException(String message) {
        super(message);
    }
}