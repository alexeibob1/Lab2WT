package by.bsuir.lab2.dao.mysql;

import by.bsuir.lab2.dao.DAOManager;
import by.bsuir.lab2.dao.UserDAO;
import by.bsuir.lab2.dao.mysql.impl.SQLUserDAOImpl;

public class MySqlDAOManager implements DAOManager {
    private static final UserDAO userDAO = new SQLUserDAOImpl();
    

    @Override
    public UserDAO getUserDAO() {
        return userDAO;
    }
}
