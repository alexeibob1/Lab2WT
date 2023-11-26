package by.bsuir.lab2.controller;

import by.bsuir.lab2.dao.connection.ConnectionPool;
import by.bsuir.lab2.dao.connection.ConnectionPoolException;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ContextListener implements ServletContextListener {
    public static final Logger LOGGER = LogManager.getLogger(ContextListener.class);

    private static final String DB_PROPERTIES_FILE = "db";

    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            ConnectionPool.getInstance().init(DB_PROPERTIES_FILE);
        } catch (ConnectionPoolException e) {
            LOGGER.fatal("Exception during initialization of connection pool!", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        try {
            ConnectionPool.getInstance().destroy();
        } catch (ConnectionPoolException e) {
            LOGGER.error("Exception during destroying of connection pool", e);
        }
    }
}
