package com.raja.urlshortener.mongo.collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.net.URL;
import java.util.Date;


@Document(collection="url-shortener")
public class RegisteredUrl {
	
	@Id
    private final String id;
    
    private final URL url;
  

	@Indexed(expireAfterSeconds=20)
    private Date createdDateTime;

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

    
    public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
}
