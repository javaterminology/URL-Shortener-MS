package com.raja.urlshortener.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.validation.Valid;
import javax.ws.rs.FormParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.raja.urlshortener.exceptions.UnknownShortenedUrlException;
import com.raja.urlshortener.exceptions.UrlShorteningException;
import com.raja.urlshortener.service.URLShorteningService;

@RestController
@RequestMapping(value="/")
public class URLShortenerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(URLShortenerController.class);
	
	@Autowired
    private URLShorteningService shorteningService;

   
    
    @RequestMapping(value="{id: [a-zA-Z0-9]+}",method={RequestMethod.GET},consumes={MediaType.ALL_VALUE},produces={MediaType.ALL_VALUE})
    public Response resolve(@Valid @PathVariable("id") String id) throws URISyntaxException {
        try {
            final URL url = shorteningService.resolveUrl(id);
            return Response.seeOther(new URI(url.toString())).build();
        } catch (UnknownShortenedUrlException e) {
            LOGGER.debug("Impossible to resolve an URL", e);
            throw new WebApplicationException(e, 404);
        }
    }

 
    @RequestMapping(value="/",method={RequestMethod.POST},consumes={MediaType.APPLICATION_FORM_URLENCODED_VALUE},produces={MediaType.ALL_VALUE})
    public String create(@Valid @FormParam("url") String originalUrl) {
        try {
            return shorteningService.shortenUrl(originalUrl).toString();
        } catch (UrlShorteningException e) {
            LOGGER.debug("Bad request", e);
            throw new WebApplicationException(e, 400);
        }
    }
}
