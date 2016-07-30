package com.company.Gifterland2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;


//@EnableAutoConfiguration   //(exclude={DataSourceAutoConfiguration.class})
// Since your class is annotated with SpringBootApplication, EnableAutoConfiguration is not required.
@ComponentScan("com.company.Gifterland2.controller")
@PropertySource("classpath:/application.properties")
@SpringBootApplication
public class Gifterland2AndServletInitSpringBootJettyApp extends SpringBootServletInitializer {

    ///Servlet initializer
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(Gifterland2AndServletInitSpringBootJettyApp.class);
//    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Gifterland2AndServletInitSpringBootJettyApp.class, args);
    }

}