package com.raja.urlshortener.service;


import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raja.urlshortener.dao.URLShortenerDAOIF;
import com.raja.urlshortener.exceptions.UnknownShortenedUrlException;
import com.raja.urlshortener.exceptions.UrlShorteningException;
import com.raja.urlshortener.generator.UrlIdentifierGenerator;
import com.raja.urlshortener.mongo.collection.RegisteredUrl;


@Service(value="shorteningService")
public class URLShorteningService {

	
    protected URL baseUrl;

    @Autowired
    private UrlIdentifierGenerator urlIdentifierGenerator;

    @Autowired
    private URLShortenerDAOIF urlShortenerDAOIF;

    public URL shortenUrl(String url) {
        try {
            final URL urlToShorten = new URL(url);
            return retrieveOrCreateRegisteredUrl(urlToShorten);
        } catch (MalformedURLException e) {
            throw new UrlShorteningException("The URL to shorten is invalid: " + url, e);
        }
    }

    private URL retrieveOrCreateRegisteredUrl(URL urlToShorten) throws MalformedURLException {
        try {
            return buildCompleteShortenedUrl(urlShortenerDAOIF.findRegisteredUrl(urlToShorten));
        } catch (UnknownShortenedUrlException e) {
            final RegisteredUrl registeredUrl = new RegisteredUrl(
                    urlIdentifierGenerator.generate(),
                    urlToShorten
            );
            urlShortenerDAOIF.createRegisteredUrl(registeredUrl);
            return buildCompleteShortenedUrl(registeredUrl);
        }
    }

    private URL buildCompleteShortenedUrl(RegisteredUrl registeredUrl) throws MalformedURLException {
    	 return new URL(registeredUrl.getUrl().getProtocol(), registeredUrl.getUrl().getHost(), registeredUrl.getUrl().getPort(), "/" + registeredUrl.getId());
    }

    public URL resolveUrl(String urlId) {
        final RegisteredUrl registeredUrl = urlShortenerDAOIF.getRegisteredUrlById(urlId);
        return registeredUrl.getUrl();
    }
}
