package com.example.url_shortner.models;

import java.io.Serializable;
import java.util.Random;

public class URL {
    private String originalUrl;
    private String encodedUrl;

    public URL(String originalUrl) {
        this.originalUrl = originalUrl;
        encodedUrl();
    }

    public URL(String originalUrl, String encodedUrl) {
        this.originalUrl = originalUrl;
        this.encodedUrl = encodedUrl;
    }

    private void  encodedUrl() {
        Random random = new Random();
        this.encodedUrl = random.ints(5, 'a', 'z' + 1)
                .collect(
                        StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append
                ).toString();
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getEncodedUrl() {
        return encodedUrl;
    }

    public void setEncodedUrl(String encodedUrl) {
        this.encodedUrl = encodedUrl;
    }
}
