package com.aws.petproject.resizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;
import org.springframework.cache.annotation.ProxyCachingConfiguration;
import org.springframework.cloud.aws.autoconfigure.cache.ElastiCacheAutoConfiguration;
import org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration;
import org.springframework.cloud.aws.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.cloud.aws.core.env.stack.StackResourceRegistry;
import org.springframework.cloud.aws.core.env.stack.config.StackResourceRegistryFactoryBean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.aws.petproject.resizer"})
@EnableAutoConfiguration(exclude = {
                                     ElastiCacheAutoConfiguration.class,
                                     MailSenderAutoConfiguration.class,
                                     StackResourceRegistryFactoryBean.class,
                                     ContextStackAutoConfiguration.class,
                                     StackResourceRegistry.class,
                                     ProxyCachingConfiguration.class,
                                     SpringDataWebAutoConfiguration.class
})
public class Application {

    public static void main( String[] args ) {
        SpringApplication.run( Application.class, args );
    }

}
