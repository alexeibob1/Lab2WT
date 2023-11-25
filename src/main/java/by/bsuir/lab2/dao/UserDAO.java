package by.bsuir.lab2.dao;

import by.bsuir.lab2.bean.User;
import by.bsuir.lab2.dao.exception.DAOException;

public interface UserDAO {
    int addUser(User user) throws DAOException;
    
    boolean isEmailOrLoginExists(User user) throws DAOException;
}
