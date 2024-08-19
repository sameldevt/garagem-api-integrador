package br.com.almavivasolutions.integrador.utils.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ApiLogger {
	 private static final Logger logger = LoggerFactory.getLogger(ApiLogger.class);

    public static void logRequest(String request) {
        logger.info(request);
    }
    
    public static void logRequestDetails(String details) {
    	logger.debug(details);
    }
    
    public static void logDatabaseDetails(String details) {
    	logger.debug(details);
    }
    
    public static void logDatabaseError(String errors) {
    	logger.error(errors.toUpperCase());
    }
    
    public static void logRequestError(String errors) {
    	logger.error(errors.toUpperCase());
    }
}
