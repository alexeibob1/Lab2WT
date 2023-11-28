package by.bsuir.lab2.dao;

import by.bsuir.lab2.bean.Role;
import by.bsuir.lab2.dao.exception.DAOException;

import java.util.List;

public interface RoleDAO {
    Role getRole(int id) throws DAOException;
    
    Role getRole(String name) throws DAOException;
    
    List<Role> getRoles() throws DAOException;
}
