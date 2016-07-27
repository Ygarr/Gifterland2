package com.company.Gifterland2.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

//import org.springframework.orm.hibernate4.HibernateTransactionManager;
//import org.springframework.orm.hibernate4.LocalSessionFactoryBean;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import org.hibernate.SessionFactory;
import com.zaxxer.hikari.HikariDataSource;
import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.websystique.springmvc")
public class AppConfig {



    public static final String hibernate_connection_autocommit = "hibernate.connection.autocommit";

    public static final String hibernate_format_sql = "hibernate.format_sql";

    public static final String hibernate_hbm2ddl_auto = "hibernate.hbm2ddl.auto";

    public static final String hibernate_show_sql = "hibernate.show_sql";

    public static final String hibernate_current_session_context_class = "hibernate.current_session_context_class";

    public static final String hibernate_dialect = "hibernate.dialect";

    public static final String package_to_scan = "com.websystique.springmvc.model";

	@Autowired
	private Environment env;
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}
	
	@Bean
	public MessageSource messageSource() {
	    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	    messageSource.setBasename("messages");
	    return messageSource;
	}


    //hibernate configuration
//    @Bean
//    public SessionFactory sessionFactory() {
//        Properties properties = new Properties();
//        properties.setProperty("hibernate.show_sql", "true")
//        properties.setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory")
//        properties.setProperty("hibernate.cache.use_query_cache", "true")
//        properties.setProperty("hibernate.cache.use_second_level_cache", "true")
//        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect")
//
//        return new LocalSessionFactoryBuilder(dataSource())
//                .scanPackages("dto")
//                .addProperties(properties)
//                .buildSessionFactory();
//    }
     @Bean
    @Autowired
    public LocalSessionFactoryBean sessionFactory(DataSource datasouce)
    {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(datasouce);
        sessionFactory.setPackagesToScan(package_to_scan);
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    private Properties hibernateProperties()
    {
        Properties hibernateProperties = new Properties();
        hibernateProperties.put(hibernate_dialect, env.getProperty(hibernate_dialect));
        hibernateProperties
            .put(hibernate_current_session_context_class, env.getProperty(hibernate_current_session_context_class));
        hibernateProperties.put(hibernate_connection_autocommit, env.getProperty(hibernate_connection_autocommit));
        hibernateProperties.put(hibernate_format_sql, env.getProperty(hibernate_format_sql));
        hibernateProperties.put(hibernate_hbm2ddl_auto, env.getProperty(hibernate_hbm2ddl_auto));
        hibernateProperties.put(hibernate_show_sql, env.getProperty(hibernate_show_sql));
//        hibernateProperties.put(hibernate_connection_provider_class, env.getProperty(hibernate_connection_provider_class));
        return hibernateProperties;

    }



	//    //hibernate configuration
//    @Bean
//    @Autowired
//    public HibernateTransactionManager transactionManager(SessionFactory s) {
//        HibernateTransactionManager txManager = new HibernateTransactionManager();
//        txManager.setSessionFactory(s);
//        return txManager;
//    }
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager(sessionFactory);
		return txManager;
	}

}

