package com.raja.urlshortener.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.validation.Valid;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.raja.urlshortener.exceptions.UnknownShortenedUrlException;
import com.raja.urlshortener.exceptions.UrlShorteningException;
import com.raja.urlshortener.service.URLShorteningService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(value="/urlshortener")
public class URLShortenerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(URLShortenerController.class);
	
	@Autowired
    private URLShorteningService shorteningService;

   
    
	@RequestMapping(value = "/get")
    public Response resolve(@Valid @QueryParam("id") String id) throws URISyntaxException {
        try {
            final URL url = shorteningService.resolveUrl(id);
            LOGGER.info("originalURL...."+url.toString());
            return Response.seeOther(new URI(url.toString())).build();
        } catch (UnknownShortenedUrlException e) {
            LOGGER.debug("Impossible to resolve an URL", e);
            throw new WebApplicationException(e, 404);
        }
    }

	//,consumes={MediaType.APPLICATION_JSON_VALUE},produces={MediaType.APPLICATION_JSON_VALUE}
 
    @RequestMapping(value="/post",method={RequestMethod.POST})
    public String create(@Valid @RequestParam("url") String originalUrl) {
        try {
        	String shortenURL = shorteningService.shortenUrl(originalUrl).toString();
        	LOGGER.info("shortenURL....."+shortenURL);
            return shortenURL;
        } catch (UrlShorteningException e) {
            LOGGER.debug("Bad request", e);
            throw new WebApplicationException(e, 400);
        }
    }
}
