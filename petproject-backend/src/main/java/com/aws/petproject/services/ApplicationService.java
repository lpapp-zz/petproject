package com.aws.petproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.ec2.AmazonEC2;

/**
 * Created by 212476263 on 2016.09.27..
 */
//@Service
public class ApplicationService {
    private final AmazonEC2 amazonEc2;

    @Autowired
    public ApplicationService(AmazonEC2 amazonEc2) {
        this.amazonEc2 = amazonEc2;
    }
}
