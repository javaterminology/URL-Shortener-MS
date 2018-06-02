package com.raja.urlshortener.mongo.collection;

import org.springframework.data.mongodb.core.mapping.Document;

import java.net.URL;


@Document
public class RegisteredUrl {

    private final String id;
    private final URL url;

    public RegisteredUrl(String id, URL url) {
        this.id = id;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public URL getUrl() {
        return url;
    }

}
