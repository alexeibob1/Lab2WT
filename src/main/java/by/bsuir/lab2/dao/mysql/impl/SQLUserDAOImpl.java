package by.bsuir.lab2.dao.mysql.impl;

import by.bsuir.lab2.bean.User;
import by.bsuir.lab2.dao.UserDAO;
import by.bsuir.lab2.dao.connection.ConnectionPool;
import by.bsuir.lab2.dao.connection.ConnectionPoolException;
import by.bsuir.lab2.dao.exception.DAOException;
import by.bsuir.lab2.dao.mysql.AbstractDAO;

import java.sql.*;

public class SQLUserDAOImpl extends AbstractDAO implements UserDAO {
    private static final String ADD_USER = "INSERT INTO `client` (`username`, `email`, `password`) VALUES (?, ?, ?)";
    private static final String IS_EMAIL_OR_LOGIN_EXIST = "SELECT EXISTS(SELECT 1 FROM `client` WHERE `username`=? OR `email`=?)";
    public SQLUserDAOImpl() {
    }

    public SQLUserDAOImpl(Connection connection) {
        super(connection);
    }

    @Override
    public int addUser(User user) throws DAOException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            stmt = connection.prepareStatement(ADD_USER, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.executeUpdate();
            
            rs = stmt.getGeneratedKeys();
            rs.next();
            int userID = rs.getInt(1);
            return userID;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Exception during creating new record in `client` table in database.");
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                ConnectionPool.getInstance().closeDBResources(rs, stmt);
            } catch (SQLException e) {
                //Create Logger here
            }
        }
    }

    @Override
    public boolean isEmailOrLoginExists(User user) throws DAOException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            stmt = connection.prepareStatement(IS_EMAIL_OR_LOGIN_EXIST);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            rs = stmt.executeQuery();
            rs.next();
            return rs.getBoolean(1);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Exception during checking of existing user with the same credentials");
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                ConnectionPool.getInstance().closeDBResources(rs, stmt);
            } catch (SQLException e) {
                //Create Logger here
            }
        }
    }
}
