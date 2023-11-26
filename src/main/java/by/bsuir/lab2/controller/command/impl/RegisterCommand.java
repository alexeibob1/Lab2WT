package by.bsuir.lab2.controller.command.impl;

import by.bsuir.lab2.bean.User;
import by.bsuir.lab2.controller.command.Command;
import by.bsuir.lab2.controller.constant.CommandName;
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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import static by.bsuir.lab2.controller.constant.SessionAttribute.REGISTRATION_MESSAGE;

public class RegisterCommand implements Command {
    public static final Logger LOGGER = LogManager.getLogger(RegisterCommand.class);
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
            
            //Create code for migrating basket
            
            session.setAttribute(REGISTRATION_MESSAGE, SUCCESSFUL_REGISTRATION);
            response.sendRedirect(UrlUtil.getRefererPage(request));
        } catch (ValidationException e) {
            LOGGER.warn("Invalid user data for registration, validation failed!", e);
            session.setAttribute(REGISTRATION_MESSAGE, FAILED_REGISTRATION);
            response.sendRedirect(UrlUtil.getRefererPage(request));
        } catch (ServiceException e) {
            LOGGER.error("Unexpected error happened during registration. Registration is cancelled!", e);
            viewPath += request.getContextPath() + CommandName.GO_TO_ERROR_503_COMMAND;
            response.sendRedirect(viewPath);
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
