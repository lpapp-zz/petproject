package com.aws.petproject.ec2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by 212476263 on 2016.09.27..
 */
@Component
public class ApplicationInfoBean {

    @Value("${ami-id:N/A}")
    private String amiId;

    @Value("${hostname:N/A}")
    private String hostname;

    @Value("${instance-type:N/A}")
    private String instanceType;

    @Value("${com.aws.petproject.services/com.aws.petproject.domain:N/A}")
    private String serviceDomain;

}
