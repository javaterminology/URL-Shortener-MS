package com.raja.urlshortener.dao;

import java.net.URL;

import com.raja.urlshortener.mongo.collection.RegisteredUrl;


public interface URLShortenerDAOIF {

	 	RegisteredUrl getRegisteredUrlById(String id);

	    void createRegisteredUrl(RegisteredUrl registeredUrl);

	    RegisteredUrl findRegisteredUrl(URL url);
}
