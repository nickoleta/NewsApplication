package com.example.articles.model.custom_exceptions;

public class ParsingException extends RuntimeException {

    public ParsingException(String message) {
        super(message);
    }
}
