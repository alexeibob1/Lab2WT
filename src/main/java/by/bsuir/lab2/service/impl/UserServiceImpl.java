package by.bsuir.lab2.service.impl;

import by.bsuir.lab2.bean.User;
import by.bsuir.lab2.bean.dto.UsersTO;
import by.bsuir.lab2.dao.DAOFactory;
import by.bsuir.lab2.dao.DAOManager;
import by.bsuir.lab2.dao.StorageType;
import by.bsuir.lab2.dao.UserDAO;
import by.bsuir.lab2.dao.exception.DAOException;
import by.bsuir.lab2.service.UserService;
import by.bsuir.lab2.service.exception.ServiceException;
import by.bsuir.lab2.service.exception.ValidationException;
import by.bsuir.lab2.service.util.SHA256;
import by.bsuir.lab2.service.validation.Validator;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static final DAOManager daoManager = DAOFactory.getDAOManager(StorageType.MY_SQL);

    private static final UserDAO userDAO = daoManager.getUserDAO();

    @Override
    public int register(User user) throws ServiceException, ValidationException {
        if (!Validator.isRegistrationDataValid(user)) {
            throw new ValidationException("Registration data is invalid.");
        }

        try {
            if (userDAO.isEmailOrLoginExists(user)) {
                throw new ValidationException("User with specified login or email is already registered.");
            }
            String passwordHash = SHA256.getSHA256Hash(user.getPassword());
            user.setPassword(passwordHash);
            return userDAO.addUser(user);
        } catch (DAOException e) {
            throw new ServiceException("Error during registration of a new user.");
        }
    }

    @Override
    public User login(String usernameOrEmail, String password) throws ServiceException, ValidationException {
        if (!Validator.isLoginDataValid(usernameOrEmail, password)) {
            throw new ValidationException("Invalid data for processing user login.");
        }
        
        try {
            String passwordHash = SHA256.getSHA256Hash(password);
            return userDAO.getUser(usernameOrEmail, passwordHash);
        } catch (DAOException e) {
            throw new ServiceException("Exception during processing user login.", e);
        }
    }

    @Override
    public UsersTO getUsersForView() throws ServiceException {
        UsersTO usersTO = null;
        try {
            List<User> users = userDAO.getUsers();
            usersTO = new UsersTO();
            usersTO.setUsers(users);
        } catch (DAOException e) {
            throw new ServiceException("Exception during getting list of all users", e);
        }
        
        return usersTO;
    }

    @Override
    public User getUser(int userID) throws ServiceException {
        User user = null;
        try {
            user = userDAO.getUser(userID);
        } catch (DAOException e) {
            throw new ServiceException("Exception during getting user by ID", e);
        }
        return user;
    }

    @Override
    public void updateUser(User user) throws ServiceException {
        //VALIDATOR
        
        try {
            userDAO.updateUser(user);
        } catch (DAOException e) {
            throw new ServiceException("Exception during updating user info", e);
        }
    }
}