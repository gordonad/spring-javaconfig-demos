package com.gordondickens.javaconfig;

import com.gordondickens.javaconfig.service.CustomerService;
import com.gordondickens.javaconfig.service.CustomerServiceImpl;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author Gordon Dickens (dickeg01)
 * @see org.springframework.context.annotation.ClassPathBeanDefinitionScanner
 * @see org.springframework.beans.factory.support.DefaultListableBeanFactory
 * @see org.springframework.beans.factory.support.BeanDefinitionReader
 * @see org.springframework.context.annotation.ConfigurationClassPostProcessor
 * @see org.springframework.beans.factory.annotation.InjectionMetadata
 * @see org.springframework.beans.CachedIntrospectionResults
 * @see org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor
 * @see
 */
@Configuration
@ComponentScan
@EnableTransactionManagement
@PropertySource("classpath:/properties/jdbc-hsql.properties")
public class AppConfig {
    private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    Environment environment;

    @PostConstruct
    public void logBeans() {
        logger.debug(StringUtils.repeat('*', 78));
        for (String bean : applicationContext.getBeanDefinitionNames()) {
            logger.debug("BEAN '{}'", bean);
        }
        logger.debug(StringUtils.repeat('*', 78));
        logger.debug("ENV: '{}'", environment.toString());
        logger.debug(StringUtils.repeat('*', 78));
    }

    @Value("#{ environment['jdbc.url'] }")
    protected String databaseUrl;

    @Value("#{ environment['jdbc.username'] }")
    protected String databaseUserName = "";

    @Value("#{ environment['jdbc.password'] }")
    protected String databasePassword = "";

    @Value("#{ environment['database.driverClassName'] }")
    protected String driverClassName;

    @Value("#{ environment['jdbc.validation.query'] }")
    protected String databaseValidationQuery;

    @Bean
    public CustomerService customerService() {
        return new CustomerServiceImpl();
    }

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(databaseUrl);
        dataSource.setUsername(databaseUserName);
        dataSource.setPassword(databasePassword);
        dataSource.setValidationQuery(databaseValidationQuery);
        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(true);
        dataSource.setTestWhileIdle(true);
        dataSource.setTimeBetweenEvictionRunsMillis(1800000);
        dataSource.setNumTestsPerEvictionRun(3);
        dataSource.setMinEvictableIdleTimeMillis(1800000);
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource());
    }

    @Bean
    public DatabasePopulator databasePopulator(DataSource dataSource) {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.setContinueOnError(true);
        populator.setIgnoreFailedDrops(true);
        populator.addScript(new ClassPathResource("/db/hsqldb/customer-ddl.sql"));
        populator.addScript(new ClassPathResource("/db/hsqldb/customer-dml.sql"));
        try {
            populator.populate(dataSource.getConnection());
        } catch (SQLException e) {
            logger.error(e.getLocalizedMessage(), e);
        }
        return populator;
    }
}
