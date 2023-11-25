package by.bsuir.lab2.controller;

import by.bsuir.lab2.dao.connection.ConnectionPool;
import by.bsuir.lab2.dao.connection.ConnectionPoolException;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
    //Logger

    private static final String DB_PROPERTIES_FILE = "db";

    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            ConnectionPool.getInstance().init(DB_PROPERTIES_FILE);
        } catch (ConnectionPoolException e) {
            //Logger
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        try {
            ConnectionPool.getInstance().destroy();
        } catch (ConnectionPoolException e) {
            //Logger
        }
    }
}
