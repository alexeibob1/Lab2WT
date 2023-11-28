package by.bsuir.lab2.service;

import by.bsuir.lab2.bean.Role;
import by.bsuir.lab2.bean.dto.RolesTO;
import by.bsuir.lab2.service.exception.ServiceException;

public interface RoleService {
    Role getRole(int id) throws ServiceException;
    
    Role getRole(String name) throws ServiceException;
    
    RolesTO getRoles() throws ServiceException;
}
