package by.bsuir.lab2.service.impl;

import by.bsuir.lab2.bean.Role;
import by.bsuir.lab2.bean.dto.RolesTO;
import by.bsuir.lab2.dao.*;
import by.bsuir.lab2.dao.exception.DAOException;
import by.bsuir.lab2.service.RoleService;
import by.bsuir.lab2.service.exception.ServiceException;

import java.util.List;

public class RoleServiceImpl implements RoleService {
    private static final DAOManager daoManager = DAOFactory.getDAOManager(StorageType.MY_SQL);

    private static final RoleDAO roleDAO = daoManager.getRoleDAO();
    @Override
    public Role getRole(int id) throws ServiceException {
        Role role = null;
        try {
            role = roleDAO.getRole(id);
        } catch (DAOException e) {
            throw new ServiceException("Exception during getting role by ID", e);
        }
        return role;
    }

    @Override
    public Role getRole(String name) throws ServiceException {
        Role role = null;
        try {
            role = roleDAO.getRole(name);
        } catch (DAOException e) {
            throw new ServiceException("Exception during getting role by name", e);
        }
        return role;
    }

    @Override
    public RolesTO getRoles() throws ServiceException {
        RolesTO rolesTO = null;
        try {
            List<Role> roles = roleDAO.getRoles();
            rolesTO = new RolesTO();
            rolesTO.setRoles(roles);
        } catch (DAOException e) {
            throw new ServiceException("Exception during getting all roles", e);
        }
        return rolesTO;
    }
}
