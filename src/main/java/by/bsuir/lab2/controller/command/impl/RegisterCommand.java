package by.bsuir.lab2.controller.command.impl;

import by.bsuir.lab2.bean.User;
import by.bsuir.lab2.controller.command.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class RegisterCommand implements Command {
    private static final String EMAIL_PARAM = "reg_email";
    private static final String LOGIN_PARAM = "reg_login";
    private static final String PASSWORD_PARAM = "reg_password";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = createUser(request);
        HttpSession session = request.getSession();
        StringBuilder viewPath = new StringBuilder();
        try {

        } catch (Exception e) {

        }
    }

    private User createUser(HttpServletRequest request) {
        User user = new User();
        user.setEmail(request.getParameter(EMAIL_PARAM));
        user.setUsername(request.getParameter(LOGIN_PARAM));
        user.setPassword(request.getParameter(PASSWORD_PARAM));
        return user;
    }
}
