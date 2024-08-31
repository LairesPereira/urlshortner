package com.example.url_shortner.exceptions;

public class UrlDaoException extends RuntimeException {
    public UrlDaoException() {}

    public UrlDaoException(String message) {
        super(message);
    }
}
