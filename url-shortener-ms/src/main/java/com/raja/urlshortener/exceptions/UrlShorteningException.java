package com.raja.urlshortener.exceptions;


public class UrlShorteningException extends RuntimeException {


	private static final long serialVersionUID = -6166081101865926642L;

	public UrlShorteningException(String message, Throwable cause) {
        super(message, cause);
    }

}
