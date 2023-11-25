package by.bsuir.lab2.dao.connection;

import java.io.*;
import java.sql.*;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private BlockingQueue<Connection> connections;
    private String driver;
    private String connectionURL;
    private String username;
    private String password;
    private int poolSize;
    private static final ConnectionPool INSTANCE = new ConnectionPool();

    private ConnectionPool() {
    }

    public static ConnectionPool getInstance() {
        return INSTANCE;
    }

    public BlockingQueue<Connection> getConnections() {
        return connections;
    }

    public void init(String propertiesFileName) throws ConnectionPoolException {
        Properties dbProperties = new Properties();
        try (FileInputStream dbPropertiesFile = new FileInputStream(propertiesFileName + ".properties")) {
            dbProperties.load(dbPropertiesFile);
            driver = dbProperties.getProperty(DatabaseParameter.DB_DRIVER);
            connectionURL = dbProperties.getProperty(DatabaseParameter.DB_CONNECTION_URL);
            username = dbProperties.getProperty(DatabaseParameter.DB_USERNAME);
            password = dbProperties.getProperty(DatabaseParameter.DB_PASSWORD);
            poolSize = Integer.parseInt(dbProperties.getProperty(DatabaseParameter.DB_POOLSIZE));

            Class.forName(driver);
            connections = new ArrayBlockingQueue<>(poolSize);
            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(connectionURL, username, password);
                connections.add(new PooledConnection(connection));
            }
        } catch (IOException | ClassNotFoundException | SQLException e) {
            throw new ConnectionPoolException("Error during initialization of connection pool", e);
        }
    }

    public Connection getConnection() throws ConnectionPoolException {
        Connection connection = null;
        try {
            connection = connections.take();
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Error during extracting connection from connection pool", e);
        }
        return connection;
    }

    public void closeDBResources(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
        closeResultSet(resultSet);
        closeStatement(statement);
        closeConnection(connection);
    }

    public void closeDBResources(Connection connection, Statement statement) throws SQLException {
        closeDBResources(connection, statement, null);
    }

    public void closeDBResources(Statement... statements) throws SQLException {
        for (Statement statement : statements) {
            closeStatement(statement);
        }
    }

    public void closeDBResources(ResultSet resultSet, Statement... statements) throws SQLException {
        closeResultSet(resultSet);
        for (Statement statement : statements) {
            closeStatement(statement);
        }
    }

    private void closeConnection(Connection connection) throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    private void closeStatement(Statement statement) throws SQLException {
        if (statement != null) {
            statement.close();
        }
    }

    private void closeResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
    }

    public void destroy() throws ConnectionPoolException {
        for (int i = 0; i < poolSize; i++) {
            try {
                Connection connection = connections.take();
                if (!connection.getAutoCommit()) {
                    connection.commit();
                }
                ((PooledConnection)connection).totalClose();
            } catch (SQLException | InterruptedException e) {
                throw new ConnectionPoolException("Error during closing of the connection pool.", e);
            }
        }
    }
}
