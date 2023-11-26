package by.bsuir.lab2.dao.mysql.impl;

import by.bsuir.lab2.bean.Role;
import by.bsuir.lab2.bean.User;
import by.bsuir.lab2.dao.UserDAO;
import by.bsuir.lab2.dao.connection.ConnectionPool;
import by.bsuir.lab2.dao.connection.ConnectionPoolException;
import by.bsuir.lab2.dao.exception.DAOException;
import by.bsuir.lab2.dao.mysql.AbstractDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class SQLUserDAOImpl extends AbstractDAO implements UserDAO {
    public static final Logger LOGGER = LogManager.getLogger(SQLUserDAOImpl.class);
    private static final String ADD_USER = "INSERT INTO `client` (`username`, `email`, `password`) VALUES (?, ?, ?)";
    public static final String GET_USER = "SELECT `client`.`id`, `username`, `role_id` " +
            "FROM `client` INNER JOIN `role` ON `client`.`role_id` = `role`.`id` " +
            "WHERE `password`=? AND (`email`=? OR `username`=?)";
    public static final String GET_USER_NAME = "SELECT `name` FROM `client` WHERE `id`=?";
    private static final String IS_EMAIL_OR_LOGIN_EXIST = "SELECT EXISTS(SELECT 1 FROM `client` WHERE `username`=? OR `email`=?)";
    private static final String SET_USER_INFO = "";

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
            return rs.getInt(1);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Exception during creating new record in `client` table in database.");
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                ConnectionPool.getInstance().closeDBResources(rs, stmt);
            } catch (SQLException e) {
                LOGGER.warn("Exception during closing resources of the database.", e);
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

    @Override
    public User getUser(String usernameOrEmail, String password) throws DAOException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            stmt = connection.prepareStatement(GET_USER);
            stmt.setString(1, password);
            stmt.setString(2, usernameOrEmail);
            stmt.setString(3, usernameOrEmail);
            rs = stmt.executeQuery();
            rs.next();
            if (rs.getRow() == 0) {
                return null;
            }
            return formUser(rs);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Exception during getting user by login/email and password from database", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                ConnectionPool.getInstance().closeDBResources(rs, stmt);
            } catch (SQLException e) {
                LOGGER.warn("Exception during closing resources of the database.", e);
            }
        }
    }

    @Override
    public String getUsersName(int usedID) throws DAOException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            stmt = connection.prepareStatement(GET_USER_NAME);
            stmt.setInt(1, usedID);
            rs = stmt.executeQuery();
            rs.next();
            String name = rs.getString(1);
            return name;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Exception during getting user's name from database.", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                ConnectionPool.getInstance().closeDBResources(rs, stmt);
            } catch (SQLException e) {
                LOGGER.warn("Exception during closing resources of the database.", e);
            }
        }
    }

    @Override
    public void setUserInfo(User user) throws DAOException {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = getConnection();
            stmt = connection.prepareStatement(SET_USER_INFO);
//            stmt.setString(1, user.getName());
//            stmt.setString(2, user.getMiddleName());
//            stmt.setString(3, user.getSurname());
//            stmt.setString(4, user.getAdress());
//            stmt.setString(5, user.getPassport());
//            stmt.setString(6, user.getTelephone());
//            stmt.setInt(7, user.getIdUser());
            stmt.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Exception during adding info about user to table 'client' in database.", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                ConnectionPool.getInstance().closeDBResources(stmt);
            } catch (SQLException e) {
                LOGGER.warn("Exception during closing resources of the database.", e);
            }
        }
    }
    
    private User formUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt(1));
        user.setUsername(rs.getString(2));
        user.setRole(Role.values()[rs.getInt(3) - 1]);
        return user;
    }
    
}
