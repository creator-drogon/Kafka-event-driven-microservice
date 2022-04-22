package com.microservices.demo.twitter.to.kafka.service.runner;

import com.microservices.demo.config.TwitterToKafkaServiceConfigData;
import com.microservices.demo.twitter.to.kafka.service.listener.TwitterStreamListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import twitter4j.FilterQuery;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

import javax.annotation.PreDestroy;
import java.util.Arrays;

@Slf4j
@Component
public class StreamRunnerImpl implements TwitterKafkaStreamRunner{

    private final TwitterToKafkaServiceConfigData config;
    private final TwitterStreamListener streamListener;
    private TwitterStream twitterStream;

    public StreamRunnerImpl(TwitterStreamListener twitterStreamListener,TwitterToKafkaServiceConfigData twitterToKafkaConfig){
        this.config = twitterToKafkaConfig;
        this.streamListener = twitterStreamListener;

    }



    @Override
    public void start() throws TwitterException {

        twitterStream = new TwitterStreamFactory().getInstance().addListener(streamListener);
        String[] keywords = config.getTwitterKeywords().toArray(new String[0]);
        FilterQuery filterQuery = new FilterQuery(keywords);
        twitterStream.filter(filterQuery);
        log.info("Started filtering the Twitter Stream for {}", Arrays.toString(keywords));

    }

    @PreDestroy
    void shutDown(){
        if(twitterStream!=null){
            twitterStream.shutdown();
        }
    }

}
