package by.bsuir.lab2.service;

import by.bsuir.lab2.bean.Locale;
import by.bsuir.lab2.bean.User;
import by.bsuir.lab2.bean.UsersTO;
import by.bsuir.lab2.service.exception.ServiceException;
import by.bsuir.lab2.service.exception.ValidationException;

public interface UserService {
    int register(User user) throws ServiceException, ValidationException;
    
    User login(String usernameOrEmail, String password) throws ServiceException, ValidationException;
    
    UsersTO getUsersForView() throws ServiceException;
    
    User getUser(int userID) throws ServiceException;
}
