package com.microservices.demo.twitter.to.kafka.service;


import com.microservices.demo.config.TwitterToKafkaServiceConfigData;
import com.microservices.demo.twitter.to.kafka.service.runner.StreamRunnerImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@SpringBootApplication
@Slf4j
@ComponentScan(basePackages =  "com.microservices")
public class TwitterToKafkaApplication implements CommandLineRunner {
	final Logger LOG = LoggerFactory.getLogger(TwitterToKafkaApplication.class);

	private final TwitterToKafkaServiceConfigData twitterToKafkaConfig;

	private final StreamRunnerImpl streamRunner;

	@Autowired
	TwitterToKafkaApplication(TwitterToKafkaServiceConfigData twitterToKafkaConfig, StreamRunnerImpl streamRunnerImpl){
		this.twitterToKafkaConfig = twitterToKafkaConfig;
		this.streamRunner = streamRunnerImpl;
	}

	public static void main(String[] args) {
		SpringApplication.run(TwitterToKafkaApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		LOG.info("App starts...");
        LOG.info(Arrays.toString(twitterToKafkaConfig.getTwitterKeywords().toArray(new String[] {})));
        LOG.info(twitterToKafkaConfig.getWelcomeMessage());
		streamRunner.start();

	}
}
