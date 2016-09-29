package com.aws.petproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by 212476263 on 2016.09.28..
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.aws.petproject"})
public class Application {

    public static void main( String[] args ) {
        SpringApplication.run( Application.class, args );
    }

}
