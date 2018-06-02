package com.raja.urlshortener.exceptions;

import java.net.URL;


public class UnknownShortenedUrlException extends RuntimeException {

    public UnknownShortenedUrlException(String id) {
        super("Unknown URL with ID: " + id);
    }

    public UnknownShortenedUrlException(URL url) {
        super("Unknown URL: " + url);
    }
}
