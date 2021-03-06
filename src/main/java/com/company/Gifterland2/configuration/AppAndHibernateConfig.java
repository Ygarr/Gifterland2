package com.company.Gifterland2.configuration;

//import org.hibernate.cfg.Environment;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.sql.DataSource;
import java.util.Properties;

//import org.hibernate.cfg.Environment;

//import org.springframework.core.env.Environment;

//import org.springframework.orm.hibernate4.HibernateTransactionManager;
//import org.springframework.orm.hibernate4.LocalSessionFactoryBean;


@Configuration
@EnableTransactionManagement
@PropertySource(
    {
        "classpath:application.properties"
    })
//@EnableWebMvc
@ComponentScan(basePackages = "com.company.Gifterland2")
public class AppAndHibernateConfig {


    //For hibernateProperties
    public static final String hibernate_connection_autocommit = "hibernate.connection.autocommit";
    public static final String hibernate_format_sql = "hibernate.format_sql";
    public static final String hibernate_hbm2ddl_auto = "hibernate.hbm2ddl.auto";
    public static final String hibernate_show_sql = "hibernate.show_sql";
    public static final String hibernate_current_session_context_class = "hibernate.current_session_context_class";
    public static final String hibernate_dialect = "hibernate.dialect";

    //for LocalSessionFactoryBean bean(method)
    public static final String package_to_scan = "com.company.Gifterland2.model";

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


    @Bean(destroyMethod = "close")
    @Autowired
    public DataSource dataSource()
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        return dataSource;
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


///Redundant??????Don't you say
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
//    /**
//     * Declare the transaction manager witout hui-birnate
//     */
//    @Bean
//    public JpaTransactionManager transactionManager() {
//        JpaTransactionManager transactionManager =               new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(   entityManagerFactory.getObject());
//        return transactionManager;
//    }

}

