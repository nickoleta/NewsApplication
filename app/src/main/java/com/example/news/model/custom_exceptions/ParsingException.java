package com.example.news.model.custom_exceptions;

public class ParsingException extends RuntimeException {

    public ParsingException(String message) {
        super(message);
    }
}
