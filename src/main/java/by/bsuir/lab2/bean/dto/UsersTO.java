package by.bsuir.lab2.bean.dto;

import by.bsuir.lab2.bean.User;

import java.util.List;

public class UsersTO {
    private List<User> users;
    
    //pagination will be later


    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public UsersTO() {
    }
}
