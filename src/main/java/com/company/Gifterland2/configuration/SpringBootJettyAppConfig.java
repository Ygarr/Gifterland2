package com.company.Gifterland2.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;



@EnableAutoConfiguration
@ComponentScan("com.websystique.springmvc.controller")
@PropertySource("classpath:/application.properties")
//@SpringBootApplication
public class SpringBootJettyAppConfig extends SpringBootServletInitializer {




    ////Caused by: java.lang.NoSuchMethodError: org.springframework.core.ResolvableType.forInstance(Ljava/lang/Object;)Lorg/springframework/core/ResolvableType;
    ////spring-core
    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootJettyAppConfig.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootJettyAppConfig.class);
    }

}