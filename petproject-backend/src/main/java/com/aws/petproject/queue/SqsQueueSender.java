package com.aws.petproject.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.amazonaws.services.sqs.AmazonSQS;

/**
 * Created by 212476263 on 2016.10.05..
 */
@Component
public class SqsQueueSender {

    private String queueName = "petproject-resizer";

    private final QueueMessagingTemplate queueMessagingTemplate;

    @Autowired
    public SqsQueueSender(AmazonSQS amazonSqs) {
        this.queueMessagingTemplate = new QueueMessagingTemplate(amazonSqs);
    }

    public void send(String message) {
        this.queueMessagingTemplate.send(queueName, MessageBuilder.withPayload(message).build());
    }

}
