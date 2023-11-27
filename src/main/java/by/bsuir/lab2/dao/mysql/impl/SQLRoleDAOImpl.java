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

public class SQLRoleDAOImpl extends AbstractDAO implements RoleDAO {
    public static final Logger LOGGER = LogManager.getLogger(SQLRoleDAOImpl.class);
    
    private static final String GET_ALL_ROLES = "SELECT `role`.`id` FROM `role`";
    @Override
    public List<Role> getRoles() throws DAOException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            stmt = connection.prepareStatement(GET_ALL_ROLES);
            rs = stmt.executeQuery();
            rs.next();
            if (rs.getRow() == 0) {
                return null;
            }
            return formRoles(rs);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException("Exception during getting all roles!", e);
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
    
    private List<Role> formRoles(ResultSet rs) throws SQLException {
        List<Role> roles = new ArrayList<>();
        while (rs.next()) {
            Role role = Role.values()[rs.getInt(1) - 1];
            roles.add(role);
        }
        return roles;
    }
}
