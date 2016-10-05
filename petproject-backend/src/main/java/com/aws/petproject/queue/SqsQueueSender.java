package com.aws.petproject.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.amazonaws.services.sqs.AmazonSQSAsync;

/**
 * Created by 212476263 on 2016.10.05..
 */
@Component
public class SqsQueueSender {

    private String queueName = "petproject-resizer";

    private final QueueMessagingTemplate queueMessagingTemplate;

    @Autowired
    public SqsQueueSender(AmazonSQSAsync amazonSQSAsync) {
        this.queueMessagingTemplate = new QueueMessagingTemplate(amazonSQSAsync);
    }

    public void send(String message) {
        this.queueMessagingTemplate.send(queueName, MessageBuilder.withPayload(message).build());
    }

}
