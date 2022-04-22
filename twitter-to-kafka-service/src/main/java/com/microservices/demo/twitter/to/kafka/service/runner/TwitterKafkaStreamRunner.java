package com.microservices.demo.twitter.to.kafka.service.runner;

import twitter4j.TwitterException;

public interface TwitterKafkaStreamRunner {
    void start() throws TwitterException;
}
