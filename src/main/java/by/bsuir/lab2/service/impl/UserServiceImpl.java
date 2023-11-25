package by.bsuir.lab2.service.impl;

import by.bsuir.lab2.bean.User;
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

public class UserServiceImpl implements UserService {
    private static DAOManager daoManager = DAOFactory.getDAOManager(StorageType.MY_SQL);

    private static UserDAO userDAO = daoManager.getUserDAO();

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
            int userID = userDAO.addUser(user);
            return userID;
        } catch (DAOException e) {
            throw new ServiceException("Error during registration of a new user.");
        }
    }
}