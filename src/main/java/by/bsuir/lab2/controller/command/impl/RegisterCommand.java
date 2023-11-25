package by.bsuir.lab2.controller.command.impl;

import by.bsuir.lab2.bean.Role;
import by.bsuir.lab2.bean.User;
import by.bsuir.lab2.controller.command.Command;
import by.bsuir.lab2.controller.constant.SessionAttribute;
import by.bsuir.lab2.controller.constant.ViewPath;
import by.bsuir.lab2.service.ServiceFactory;
import by.bsuir.lab2.service.UserService;
import by.bsuir.lab2.service.exception.ServiceException;
import by.bsuir.lab2.service.exception.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URI;

public class RegisterCommand implements Command {
    private static final String EMAIL_PARAM = "email";
    private static final String LOGIN_PARAM = "username";
    private static final String PASSWORD_PARAM = "password";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = createUser(request);
        HttpSession session = request.getSession();
        StringBuilder viewPath = new StringBuilder();
        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            int userID = userService.register(user);
            session.setAttribute(SessionAttribute.ID_USER, userID);
            session.setAttribute(SessionAttribute.USERNAME, user.getUsername());
            session.setAttribute(SessionAttribute.ROLE, Role.CLIENT);
            
            //Create code for migrating basket
            
            
        } catch (ValidationException e) {
            //Invalid registration data
        } catch (ServiceException e) {
            //Error during registration (503)
        }
        response.sendRedirect(ViewPath.REDIRECT_REGISTRATION_FORM);
    }

    private User createUser(HttpServletRequest request) {
        User user = new User();
        user.setEmail(request.getParameter(EMAIL_PARAM));
        user.setUsername(request.getParameter(LOGIN_PARAM));
        user.setPassword(request.getParameter(PASSWORD_PARAM));
        return user;
    }
}
