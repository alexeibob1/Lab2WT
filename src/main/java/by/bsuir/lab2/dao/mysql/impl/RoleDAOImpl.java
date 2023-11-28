package by.bsuir.lab2.dao.mysql.impl;

import by.bsuir.lab2.bean.Role;
import by.bsuir.lab2.bean.User;
import by.bsuir.lab2.dao.RoleDAO;
import by.bsuir.lab2.dao.connection.ConnectionPool;
import by.bsuir.lab2.dao.connection.ConnectionPoolException;
import by.bsuir.lab2.dao.exception.DAOException;
import by.bsuir.lab2.dao.mysql.AbstractDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDAOImpl extends AbstractDAO implements RoleDAO {
    public static final Logger LOGGER = LogManager.getLogger(RoleDAOImpl.class);
    
    private static final String GET_ROLE_BY_ID = "SELECT `role`.`id`, `role`.`name` FROM `role` WHERE `role`.`id`=?";
    private static final String GET_ROLE_BY_NAME = "SELECT `role`.`id`, `role`.`name` FROM `role` WHERE `role`.`name`=?";
    private static final String GET_ALL_ROLES = "SELECT `role`.`id`, `role`.`name` FROM `role`";

    public RoleDAOImpl() {
    }

    public RoleDAOImpl(Connection connection) {
        super(connection);
    }
    
    @Override
    public Role getRole(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            stmt = connection.prepareStatement(GET_ROLE_BY_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            rs.next();
            if (rs.getRow() == 0) {
                return null;
            }
            return formRole(rs);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Exception during getting role by id", e);
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
    public Role getRole(String name) throws DAOException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            stmt = connection.prepareStatement(GET_ROLE_BY_NAME);
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            rs.next();
            if (rs.getRow() == 0) {
                return null;
            }
            return formRole(rs);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Exception during getting role by name", e);
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
    public List<Role> getRoles() throws DAOException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Role> roles;

        try {
            connection = getConnection();
            stmt = connection.prepareStatement(GET_ALL_ROLES);
            rs = stmt.executeQuery();
            roles = formRoles(rs);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Exception during getting information about all roles.", e);
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

        if (roles.isEmpty()) {
            return new ArrayList<>();
        } else {
            return roles;
        }
    }
    
    private Role formRole(ResultSet rs) throws SQLException {
        Role role = new Role();
        role.setId(rs.getInt(1));
        role.setName(rs.getString(2));
        return role;
    }
    
    private List<Role> formRoles(ResultSet rs) throws SQLException {
        List<Role> roles = new ArrayList<>();
        while (rs.next()) {
            Role user = formRole(rs);
            roles.add(user);
        }
        return roles;
    }
}
