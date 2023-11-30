package by.bsuir.lab2.dao.mysql;

import by.bsuir.lab2.dao.DAOManager;
import by.bsuir.lab2.dao.ProductDAO;
import by.bsuir.lab2.dao.RoleDAO;
import by.bsuir.lab2.dao.UserDAO;
import by.bsuir.lab2.dao.mysql.impl.ProductDAOImpl;
import by.bsuir.lab2.dao.mysql.impl.RoleDAOImpl;
import by.bsuir.lab2.dao.mysql.impl.UserDAOImpl;

public class MySqlDAOManager implements DAOManager {
    private static final UserDAO userDAO = new UserDAOImpl();
    
    private static final RoleDAO roleDAO = new RoleDAOImpl();
    
    private static final ProductDAO productDAO = new ProductDAOImpl();

    @Override
    public UserDAO getUserDAO() {
        return userDAO;
    }

    @Override
    public RoleDAO getRoleDAO() {
        return roleDAO;
    }

    @Override
    public ProductDAO getProductDAO() {
        return productDAO;
    }


}
