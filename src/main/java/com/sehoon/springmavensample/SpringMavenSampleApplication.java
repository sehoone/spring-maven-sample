package com.sehoon.springmavensample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.sehoon.springmavensample.common.config.ApplicationProperties;

@EnableConfigurationProperties(ApplicationProperties.class)
@SpringBootApplication
public class SpringMavenSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMavenSampleApplication.class, args);
	}

}
