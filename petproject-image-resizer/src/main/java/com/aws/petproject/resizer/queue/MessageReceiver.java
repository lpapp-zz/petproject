package com.aws.petproject.resizer.queue;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Component;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.aws.petproject.resizer.services.ResizerService;

/**
 * Created by 212476263 on 2016.10.05..
 */
@Component
public class MessageReceiver {

    @Autowired
    private ResizerService resizerService;

    private final QueueMessagingTemplate queueMessagingTemplate;

    @Autowired
    public MessageReceiver(AmazonSQSAsync amazonSQSAsync) {
        this.queueMessagingTemplate = new QueueMessagingTemplate(amazonSQSAsync);
    }

    @MessageMapping("petproject-resizer")
    public void messageHandler(String picturePath) throws IOException {
        System.out.println("++++++++++++ " + picturePath);
        resizerService.resizeImage( picturePath );
    }

}
