package com.aws.petproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.ProxyCachingConfiguration;
import org.springframework.cloud.aws.autoconfigure.cache.ElastiCacheAutoConfiguration;
import org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration;
import org.springframework.cloud.aws.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.cloud.aws.core.env.stack.StackResourceRegistry;
import org.springframework.cloud.aws.core.env.stack.config.StackResourceRegistryFactoryBean;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by 212476263 on 2016.09.28..
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.aws.petproject"})
@EnableAutoConfiguration(exclude = {
                                     ElastiCacheAutoConfiguration.class,
                                     MailSenderAutoConfiguration.class,
                                     StackResourceRegistryFactoryBean.class,
                                     ContextStackAutoConfiguration.class,
                                     StackResourceRegistry.class,
                                     ProxyCachingConfiguration.class
})
public class Application {

    public static void main( String[] args ) {
        SpringApplication.run( Application.class, args );
    }

}
