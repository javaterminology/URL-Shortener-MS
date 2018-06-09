package com.raja.urlshortener.generator;

import com.raja.urlshortener.main.URLShortenerApplication;
import com.raja.urlshortener.utils.Base62;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;

@Component
public class Base62TimestampUrlIdentifierGenerator implements UrlIdentifierGenerator {

	//the AtomicInteger class is a wrapper class for an int value that allows it to be updated atomically.
	//it supports lock free thread safety programming(without synchronization) in multi threaded environment 
	//and it will b used when there r multiple threads updating some counter variable of type int.ex: id
    protected AtomicInteger counter = new AtomicInteger();
    private static final Logger LOG = LoggerFactory.getLogger(Base62TimestampUrlIdentifierGenerator.class);

    public String generate() {
    	
    	//anonymous class implementation
    	 final int value = counter.getAndUpdate(new IntUnaryOperator() {
 			@Override
 			public int applyAsInt(int operand) {
 		        LOG.info("operand------>"+operand);
 				return (operand+1)%1000;
 			}
 		});
    	
        final int counterValue = counter.getAndUpdate((operand) ->(operand + 1) % 1000);
        
      
        final long base10Id = Long.valueOf("" + counterValue + System.currentTimeMillis());
        LOG.info("base10Id------>"+base10Id);
        
        return Base62.fromBase10(base10Id);
    }

}
