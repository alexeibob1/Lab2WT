package by.bsuir.lab2.service;

import by.bsuir.lab2.bean.User;
import by.bsuir.lab2.service.exception.ServiceException;
import by.bsuir.lab2.service.exception.ValidationException;

public interface UserService {
    int register(User user) throws ServiceException, ValidationException;
}
