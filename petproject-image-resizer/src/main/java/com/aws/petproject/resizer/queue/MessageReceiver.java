package com.aws.petproject.resizer.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Component;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsync;

/**
 * Created by 212476263 on 2016.10.05..
 */
@Component
public class MessageReceiver {

    private final QueueMessagingTemplate queueMessagingTemplate;

    @Autowired
    public MessageReceiver(AmazonSQSAsync amazonSQSAsync) {
        this.queueMessagingTemplate = new QueueMessagingTemplate(amazonSQSAsync);
    }

    @MessageMapping("randomQName")
    public void messageHandler(String payload) {
        System.out.println("++++++++++++ " + payload);
        //resize
    }

}
