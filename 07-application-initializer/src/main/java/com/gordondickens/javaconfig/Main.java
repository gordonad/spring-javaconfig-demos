package com.gordondickens.javaconfig;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @author Gordon Dickens (gordonad)
 * @since 2013-04-18
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static final String ENVIRONMENT_KEY = "runtime.database";

    /**
     * @param args java cli args
     * @throws Throwable
     */
    public static void main(String[] args) throws Throwable {
        logger.info("Application Started");
        try {
            AnnotationConfigApplicationContext context =
                    new AnnotationConfigApplicationContext();

            // Initialize the Spring Application Context
            initialize(context);
            // Register the JavaConfig class(es)
            context.register(AppConfig.class);
            // Refresh the Context - REQUIRED after setting the environment profile(s)
            context.refresh();
            
            // Register the proper shutdown hook for the application
            context.registerShutdownHook();
        } catch (Throwable t) {
            logger.error(t.getLocalizedMessage(), t);
        }
    }

    /**
     * @param context The Spring Application Context 
     */
    private static void initialize(final ConfigurableApplicationContext context) {
        logger.trace("***** INITIALIZE CONTEXT () *****");
        // Extract from Java Properties (-Druntime.database=hsql or h2 or derby)
        Environment env = context.getEnvironment();

        if (env.containsProperty(ENVIRONMENT_KEY)) {
            final String value = env.getProperty(ENVIRONMENT_KEY);
            logger.debug("Environment Key found: '{}'='{}'", ENVIRONMENT_KEY, value);

            if (StringUtils.trimToEmpty(value).equals(Env.H2)) {
                context.getEnvironment().setActiveProfiles(Env.H2);
            }
            logger.info("\n\n****** Setting Environment to '{}' ******\n\n", Env.H2);
        } else {
            logger.info("\n\n****** Setting Environment to '{}' ******\n\n", Env.HSQL);
            context.getEnvironment().setActiveProfiles(Env.HSQL);
        }

        logger.info("Application is running with profile(s): [{}]", context.getEnvironment().getActiveProfiles());
    }
}
