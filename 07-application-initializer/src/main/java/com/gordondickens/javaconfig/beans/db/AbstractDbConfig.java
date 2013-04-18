package com.gordondickens.javaconfig.beans.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Gordon Dickens (gordonad)
 * @since 2013-04-18
 */
public abstract class AbstractDbConfig {
    private static final Logger logger = LoggerFactory.getLogger(AbstractDbConfig.class);

    /**
     * Implementations are responsible for setting the dbname
     */
    public abstract String databaseName();
}
