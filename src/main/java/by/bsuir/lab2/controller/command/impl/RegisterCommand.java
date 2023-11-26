package by.bsuir.lab2.controller.command.impl;

import by.bsuir.lab2.bean.Role;
import by.bsuir.lab2.bean.User;
import by.bsuir.lab2.controller.command.Command;
import by.bsuir.lab2.controller.constant.CommandName;
import by.bsuir.lab2.controller.constant.SessionAttribute;
import by.bsuir.lab2.controller.constant.ViewPath;
import by.bsuir.lab2.controller.util.UrlUtil;
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

import static by.bsuir.lab2.controller.constant.SessionAttribute.REGISTRATION_MESSAGE_PARAM;

public class RegisterCommand implements Command {
    private static final String EMAIL_PARAM = "email";
    private static final String LOGIN_PARAM = "username";
    private static final String PASSWORD_PARAM = "password";

    private static final int SUCCESSFUL_REGISTRATION = 1;
    private static final int FAILED_REGISTRATION = 2;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = createUser(request);
        HttpSession session = request.getSession();
        String viewPath = "";
        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            int userID = userService.register(user);
            //session.setAttribute(SessionAttribute.ID_USER, userID);
            //session.setAttribute(SessionAttribute.USERNAME, user.getUsername());
            //session.setAttribute(SessionAttribute.ROLE, Role.CLIENT);
            
            //Create code for migrating basket
            
            
            session.setAttribute(REGISTRATION_MESSAGE_PARAM, SUCCESSFUL_REGISTRATION);
            response.sendRedirect(UrlUtil.getRefererPage(request));
        } catch (ValidationException e) {
            //Invalid registration data//Logger
            session.setAttribute(REGISTRATION_MESSAGE_PARAM, FAILED_REGISTRATION);
            response.sendRedirect(UrlUtil.getRefererPage(request));
        } catch (ServiceException e) {
            //Error during registration (503)//Logger
            viewPath += request.getContextPath() + CommandName.GO_TO_ERROR_503_COMMAND;
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
