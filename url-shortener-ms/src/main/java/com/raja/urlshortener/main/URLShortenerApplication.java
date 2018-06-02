package com.raja.urlshortener.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
@Configuration
@EnableAutoConfiguration(exclude={HibernateJpaAutoConfiguration.class,
		DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class,
		JmxAutoConfiguration.class,
		IntegrationAutoConfiguration.class})
@ComponentScan("com.raja.urlshortener")
public class URLShortenerApplication 
{
	private static final Logger LOG = LoggerFactory.getLogger(URLShortenerApplication.class);

	public static void main( String[] args )
	{
		try{
			SpringApplication.run(URLShortenerApplication.class, args);
			LOG.info("-----------URL Shortener Application started---------");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
