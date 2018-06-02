package com.raja.urlshortener.dao;

import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.raja.urlshortener.exceptions.UnknownShortenedUrlException;
import com.raja.urlshortener.mongo.collection.RegisteredUrl;

@Repository
public class URLShortenerDAOImpl implements URLShortenerDAOIF{

	@Autowired
    private MongoOperations mongoOperations;

    public RegisteredUrl getRegisteredUrlById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        RegisteredUrl result = this.mongoOperations.findOne(query, RegisteredUrl.class);
        if (result == null) {
            throw new UnknownShortenedUrlException(id);
        }
        return result;
    }

    public void createRegisteredUrl(RegisteredUrl registeredUrl) {
    	mongoOperations.save(registeredUrl);
    }

    public RegisteredUrl findRegisteredUrl(URL url) {
        Query query = new Query(Criteria.where("url").is(url.toString()));
        RegisteredUrl result = this.mongoOperations.findOne(query, RegisteredUrl.class);
        if (result == null) {
            throw new UnknownShortenedUrlException(url);
        }
        return result;
    }

}
