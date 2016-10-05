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
    public void messageHandler(String imagePath) throws IOException {
        System.out.println("++++++++++++ " + imagePath);
//        resizerService.resizeImage( imagePath );

        String fileName = imagePath.substring(imagePath.lastIndexOf( "/" ) + 1, imagePath.length());
        String fileFormat = fileName.substring( fileName.lastIndexOf( "." ) + 1, fileName.length());
        String resourcePath = imagePath.substring(0, imagePath.lastIndexOf( "/" ) + 1);

        String resourceUrl = resourcePath + "thumbnail." + fileName;

        resizerService.resize( imagePath, resourceUrl, 0.5 );
    }

}
