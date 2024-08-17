package br.com.almavivasolutions.integrador.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiLogger {
    private static final Logger logger = LoggerFactory.getLogger(ApiLogger.class);

    public static void logLifeCycle(String state) {
        logger.info("");
    }
}
