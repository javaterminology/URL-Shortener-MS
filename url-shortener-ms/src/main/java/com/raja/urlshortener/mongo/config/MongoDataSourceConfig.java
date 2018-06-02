package com.raja.urlshortener.mongo.config;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@Configuration
@EnableMongoRepositories(basePackages="com.raja.urlshortener.mongo.collection")
public class MongoDataSourceConfig extends AbstractMongoConfiguration{

	
	private static final Logger LOG = LoggerFactory.getLogger(MongoDataSourceConfig.class);
	
	@Value("${mongodb.db}")
	private String database;
	
	@Value("${mongodb.username}")
	private String userName;
	
	@Value("${mongodb.password}")
	private String password;
	
	@Value("${mongodb.ip}")
	private String ip;
	
	@Value("${mongodb.port}")
	private String port;
	

	@Override
	public MongoMappingContext mongoMappingContext() throws ClassNotFoundException{
		return super.mongoMappingContext();
	}
	
	@Bean
	public Mongo mongo() throws Exception{
		LOG.info("username:"+userName);
		LOG.info("username:"+ip);
		LOG.info("username:"+port);
		LOG.info("username:"+database);
		MongoClient client = new MongoClient(new ServerAddress(ip,Integer.valueOf(port)),Arrays.asList(getMongoCredintial()));
		LOG.info("MongoDB Client..."+client.getConnectPoint());
		return client;
	}
	
	@Bean(name="mongoOperations")
	public MongoOperations mongoFactory()throws Exception{
		MongoOperations db = new MongoTemplate(mongo(), getDatabaseName());
		LOG.info("MongoDB Connected ..."+db.getCollectionNames());
		return db;
	}
	
	private MongoCredential getMongoCredintial() {
		return MongoCredential.createCredential(userName, database, password.toCharArray());
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public MongoClient mongoClient() {
		return null;
	}

	protected String getDatabaseName() {
		return database;
	}

}
