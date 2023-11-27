package by.bsuir.lab2.dao;

import by.bsuir.lab2.bean.Role;
import by.bsuir.lab2.dao.exception.DAOException;

import java.util.List;

public interface RoleDAO {
    List<Role> getRoles() throws DAOException;
}
