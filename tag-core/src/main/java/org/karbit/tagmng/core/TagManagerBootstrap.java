package org.karbit.tagmng.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableFeignClients
@SpringBootApplication
@EnableMongoRepositories
@ConfigurationPropertiesScan
public class TagManagerBootstrap {

	public static void main(String[] args) {
		SpringApplication.run(TagManagerBootstrap.class, args);
	}
}
