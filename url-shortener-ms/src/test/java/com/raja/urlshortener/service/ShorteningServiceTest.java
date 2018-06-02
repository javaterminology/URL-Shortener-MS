package com.raja.urlshortener.service;

import com.raja.urlshortener.dao.URLShortenerDAOIF;
import com.raja.urlshortener.exceptions.UnknownShortenedUrlException;
import com.raja.urlshortener.exceptions.UrlShorteningException;
import com.raja.urlshortener.generator.UrlIdentifierGenerator;
import com.raja.urlshortener.mongo.collection.RegisteredUrl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


public class ShorteningServiceTest {

    @Mock
    private UrlIdentifierGenerator urlIdentifierGenerator;

    @Mock
    private URLShortenerDAOIF urlShortenerDAOIF;

    private URL baseUrl;

    @InjectMocks
    private URLShorteningService service;

    @Before
    public void setUp() throws Exception {
        service = new URLShorteningService();
        baseUrl = new URL("http://google.fr");
        service.baseUrl = baseUrl;
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_generateShortenedUrl_when_urlIsValidAndDoesNotExist() throws MalformedURLException {
        // Given
        URL urlToShorten = new URL("http://google.fr");
        String simpleId = "qwerty1234";
        when(urlIdentifierGenerator.generate()).thenReturn(simpleId);
        when(urlShortenerDAOIF.findRegisteredUrl(urlToShorten)).thenThrow(new UnknownShortenedUrlException(""));

        // When
        URL shortenedUrl = service.shortenUrl(urlToShorten.toString());

        // Then
        assertThat(shortenedUrl).isNotNull();
        assertThat(shortenedUrl.toString()).isEqualTo(baseUrl + "/" + simpleId);
    }

    @Test
    public void should_notGenerateNewShortenedUrl_when_urlAlreadyExists() throws MalformedURLException {
        // Given
        RegisteredUrl registeredUrl = new RegisteredUrl("qwerty1234", new URL("http://google.fr"));
        when(urlShortenerDAOIF.findRegisteredUrl(registeredUrl.getUrl())).thenReturn(registeredUrl);

        // When
        URL shortenedUrl = service.shortenUrl(registeredUrl.getUrl().toString());

        // Then
        assertThat(shortenedUrl).isNotNull();
        assertThat(shortenedUrl.getPath()).isEqualTo("/" + registeredUrl.getId());
    }

    @Test(expected = UrlShorteningException.class)
    public void should_throwException_when_urlIsNotValid() throws MalformedURLException {
        // Given
        String urlToShorten = "BadProtocol:/~B@dH0st]::BadPort";

        // When
        service.shortenUrl(urlToShorten);
    }

    @Test
    public void should_resolveShortenedUrl_when_urlIdExists() throws MalformedURLException {
        // Given
        String urlId = "qwerty1234";
        URL url = new URL("http://google.fr");
        when(urlShortenerDAOIF.getRegisteredUrlById(urlId)).thenReturn(new RegisteredUrl(urlId, url));

        // When
        URL originalUrl = service.resolveUrl(urlId);

        // Then
        assertThat(originalUrl).isNotNull().isEqualTo(url);
    }

}