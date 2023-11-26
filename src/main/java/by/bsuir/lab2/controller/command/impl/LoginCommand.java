package by.bsuir.lab2.controller.command.impl;

import by.bsuir.lab2.bean.Role;
import by.bsuir.lab2.bean.User;
import by.bsuir.lab2.controller.command.Command;
import by.bsuir.lab2.controller.constant.CommandName;
import by.bsuir.lab2.controller.constant.SessionAttribute;
import by.bsuir.lab2.controller.util.UrlUtil;
import by.bsuir.lab2.service.ServiceFactory;
import by.bsuir.lab2.service.UserService;
import by.bsuir.lab2.service.exception.ServiceException;
import by.bsuir.lab2.service.exception.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URI;

import static by.bsuir.lab2.controller.constant.SessionAttribute.LOGIN_MESSAGE;

public class LoginCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);
    private static final String USERNAME_OR_EMAIL_PARAM = "usernameOrEmail";
    private static final String PASSWORD_PARAM = "password";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usernameOrEmail = request.getParameter(USERNAME_OR_EMAIL_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);
        HttpSession session = request.getSession();
        String viewPath = "";
        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            User user = userService.login(usernameOrEmail, password);
            if (user != null) {
                session.setAttribute(SessionAttribute.USER_ID, user.getUserId());
                session.setAttribute(SessionAttribute.USERNAME, user.getUsername());
                session.setAttribute(SessionAttribute.ROLE, user.getRole());
                
                //Migrating basket from DB
                
                //viewPath += defineViewPath(user.getRole(), request);
                viewPath += UrlUtil.getRefererPage(request);
                response.sendRedirect(viewPath);
            } else {
                session.setAttribute(LOGIN_MESSAGE, true);
                response.sendRedirect(UrlUtil.getRefererPage(request));
            }
        } catch (ValidationException e) {
            LOGGER.warn("Invalid user credentials for login.", e);
            session.setAttribute(LOGIN_MESSAGE, true);
            response.sendRedirect(UrlUtil.getRefererPage(request));
        } catch (ServiceException e) {
            LOGGER.error("Unexpected error happened during login. Login is cancelled!", e);
            viewPath += request.getContextPath() + CommandName.GO_TO_ERROR_503_COMMAND;
            response.sendRedirect(viewPath);
        }
    }

    private String defineViewPath(Role role, HttpServletRequest request) {
        String viewPath = "";
        switch (role) {
            default:
                return UrlUtil.getRefererPage(request);
        }
    }
}
