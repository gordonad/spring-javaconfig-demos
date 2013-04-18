package com.gordondickens.javaconfig.beans.db;

import com.gordondickens.javaconfig.Env;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author Gordon Dickens (gordonad)
 * @since 2013-04-18
 */
@Profile(Env.H2)

@Configuration
public class H2Config extends AbstractDbConfig {
    private static final Logger logger = LoggerFactory.getLogger(H2Config.class);
    private static final String DBNAME = org.h2.Driver.class.getSimpleName();

    @Bean
    @Override
    public String databaseName() {
        logger.debug("The Database is configured as {}", DBNAME);
        return DBNAME;
    }

}
