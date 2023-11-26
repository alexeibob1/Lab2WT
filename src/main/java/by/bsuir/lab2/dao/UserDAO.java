package by.bsuir.lab2.dao;

import by.bsuir.lab2.bean.User;
import by.bsuir.lab2.dao.exception.DAOException;

public interface UserDAO {
    int addUser(User user) throws DAOException;

    boolean isEmailOrLoginExists(User user) throws DAOException;

    User getUser(String usernameOrEmail, String password) throws DAOException;
    
    String getUsersName(int usedID) throws DAOException;
    
    void setUserInfo(User user) throws DAOException;
}
