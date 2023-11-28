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
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl extends AbstractDAO implements UserDAO {
    public static final Logger LOGGER = LogManager.getLogger(UserDAOImpl.class);
    private static final String ADD_USER = "INSERT INTO `client` (`username`, `email`, `password`) VALUES (?, ?, ?)";
    public static final String GET_USER = "SELECT `client`.`id`, `username`, `role_id`, `role`.`name` " +
            "FROM `client` INNER JOIN `role` ON `client`.`role_id` = `role`.`id` " +
            "WHERE `password`=? AND (`email`=? OR `username`=?)";
    
    public static final String GET_USER_BY_ID = "SELECT `client`.`id`, `client`.`role_id`, `role`.`name`, `client`.`name`, `client`.`surname`, " +
            "`client`.`patronymic`, `client`.`birth_date`, `client`.`username`, `client`.`email`, `client`.`password` " +
            "FROM `client` INNER JOIN `role` ON `client`.`role_id` = `role`.`id` WHERE `client`.`id`=?";
    public static final String GET_USER_NAME = "SELECT `name` FROM `client` WHERE `id`=?";
    private static final String IS_EMAIL_OR_LOGIN_EXIST = "SELECT EXISTS(SELECT 1 FROM `client` WHERE `username`=? OR `email`=?)";
    private static final String SET_USER_INFO = "";
    
    private static final String GET_ALL_USERS = "SELECT `client`.`id`, `client`.`role_id`, `role`.`name`, `client`.`name`, `client`.`surname`, " +
            "`client`.`patronymic`, `client`.`birth_date`, `client`.`username`, `client`.`email`, `client`.`password` " +
            "FROM `client` INNER JOIN `role` ON `client`.`role_id` = `role`.`id`";
    
    private static final String UPDATE_USER = "UPDATE `client` " +
            "SET `role_id`=?, `name`=?, `surname`=?, `patronymic`=?, `birth_date`=? " +
            "WHERE `client`.`id`=?";

    public UserDAOImpl() {
    }

    public UserDAOImpl(Connection connection) {
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
    public User getUser(int userID) throws DAOException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            stmt = connection.prepareStatement(GET_USER_BY_ID);
            stmt.setInt(1, userID);
            rs = stmt.executeQuery();
            rs.next();
            if (rs.getRow() == 0) {
                return null;
            }
            return formFullUser(rs);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Exception during getting user by id", e);
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

    @Override
    public List<User> getUsers() throws DAOException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<User> users;
        
        try {
            connection = getConnection();
            stmt = connection.prepareStatement(GET_ALL_USERS);
            rs = stmt.executeQuery();
            users = formUsers(rs);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Exception during getting information about all users.", e);
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
        
        if (users.isEmpty()) {
            return new ArrayList<>();
        } else {
            return users;
        }
    }

    @Override
    public void updateUser(User user) throws DAOException {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            stmt = connection.prepareStatement(UPDATE_USER);
            stmt.setInt(1, user.getRole().getId());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getSurname());
            stmt.setString(4, user.getPatronymic());
            stmt.setDate(5, user.getBirthDate());
            stmt.setInt(6, user.getUserId());
            stmt.executeUpdate();
            connection.commit();
        } catch (SQLException | ConnectionPoolException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e1) {
                throw new DAOException("Exception during rollback transaction of updating user info.", e1);
            }
            throw new DAOException("Exception during updating users in table 'client' in database.", e);
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
        
        Role role = new Role();
        role.setId(rs.getInt(3));
        role.setName(rs.getString(4));
        user.setRole(role);
        
        return user;
    }
    
    private User formFullUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt(1));
        
        Role role = new Role();
        role.setId(rs.getInt(2));
        role.setName(rs.getString(3));
        user.setRole(role);
        
        user.setName(rs.getString(4));
        user.setSurname(rs.getString(5));
        user.setPatronymic(rs.getString(6));
        user.setBirthDate(rs.getDate(7));
        user.setUsername(rs.getString(8));
        user.setEmail(rs.getString(9));
        user.setPassword(rs.getString(10));
        return user;
    }
    
    private List<User> formUsers(ResultSet rs) throws SQLException {
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            User user = formFullUser(rs);
            users.add(user);
        }
        return users;
    }
    
}
