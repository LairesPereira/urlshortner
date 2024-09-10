package com.example.url_shortner.models;

import java.util.Random;

public class URL {
    private String originalUrl;
    private String encodedUrl;
    private boolean error;
    private String errorMessage;

    public URL() {}

    public URL(String originalUrl) {
        this.originalUrl = originalUrl;
        encodedUrl();
    }

    public URL(String originalUrl, String encodedUrl) {
        this.originalUrl = originalUrl;
        this.encodedUrl = encodedUrl;
    }

    public boolean hasError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private void encodedUrl() {
        Random random = new Random();
        this.encodedUrl = random.ints(4, 'a', 'z' + 1)
                .collect(
                        StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append
                ).toString();
    }

    public void renewEncodedUrl() {
        this.encodedUrl();
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getEncodedUrl() {
        return this.encodedUrl;
    }

    public void setEncodedUrl(String encodedUrl) {
        this.encodedUrl = encodedUrl;
    }

    @Override
    public String toString() {
        return "URL{" +
                "originalUrl='" + originalUrl + '\'' +
                ", encodedUrl='" + encodedUrl + '\'' +
                ", error=" + error +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
