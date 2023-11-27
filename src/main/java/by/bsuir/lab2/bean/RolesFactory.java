package by.bsuir.lab2.bean;

import java.util.*;

public class RolesFactory {
    private static final Map<Role, Integer> roles = new TreeMap<>();
    
    private static final RolesFactory instance = new RolesFactory();
    
    private RolesFactory() {
        roles.put(Role.CLIENT, 1);
        roles.put(Role.DOCTOR, 2);
        roles.put(Role.PHARMACIST, 3);
        roles.put(Role.ADMIN, 4);
    }
    
    public static RolesFactory getInstance() {
        return instance;
    }
    
    public int getRoleID(Role role) {
        return roles.get(role);
    }

    public List<Role> getRoles() {
        return new ArrayList<>(roles.keySet());
    }
}
