package sk.upjs.nosql_mongodb_repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories
@ComponentScan("sk.upjs.nosql_mongodb_repository")
public class MongoConfig extends AbstractMongoConfiguration {

	public final String DATABASE_NAME = "students";
	
	@Override
	protected String getDatabaseName() {
		return DATABASE_NAME;
	}

	@Override
	public MongoClient mongoClient() {
		return new MongoClient();
	}

	@Override
	protected String getMappingBasePackage() {
		return "sk.upjs.nosql_mongodb_repository";
	}
	
}
