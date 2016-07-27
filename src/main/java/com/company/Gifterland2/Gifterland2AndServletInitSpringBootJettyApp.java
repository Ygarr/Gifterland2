package com.company.Gifterland2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;


@EnableAutoConfiguration
@ComponentScan("com.websystique.springmvc.controller")
@PropertySource("classpath:/application.properties")
@SpringBootApplication
public class Gifterland2AndServletInitSpringBootJettyApp extends SpringBootServletInitializer {

    ///Servlet initializer
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Gifterland2AndServletInitSpringBootJettyApp.class);
    }


    ////Caused by: java.lang.NoSuchMethodError: org.springframework.core.ResolvableType.forInstance(Ljava/lang/Object;)Lorg/springframework/core/ResolvableType;
    ////spring-core
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Gifterland2AndServletInitSpringBootJettyApp.class, args);
    }

}