package by.bsuir.lab2.controller.command.impl.admin;

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
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;

public class EditUserCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(EditUserCommand.class);
    
    private static final String USER_ID_PARAM = "userID";
    private static final String USER_ROLE_PARAM = "role";
    private static final String USER_NAME_PARAM = "name";
    private static final String USER_SURNAME_PARAM = "surname";
    private static final String USER_PATRONYMIC_PARAM = "patronymic";
    private static final String USER_BIRTHDATE_PARAM = "birthDate";
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = formUser(request); 
        String viewPath = "";
        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            userService.updateUser(user);
            HttpSession session = request.getSession();
            session.setAttribute(SessionAttribute.SUCCESSFUL_USER_EDIT_MESSAGE, true);
            response.sendRedirect(UrlUtil.getRefererPage(request));
        } catch (ServiceException e) {
            LOGGER.error("Exception during updating user information", e);
            viewPath += request.getContextPath() + CommandName.GO_TO_ERROR_503_COMMAND;
            response.sendRedirect(viewPath);
        }
    }
    
    private User formUser(HttpServletRequest request) {
        User user = new User();
        try {
            user.setUserId(Integer.parseInt(request.getParameter(USER_ID_PARAM)));
            Role role = formRole(request);
            user.setRole(role);
            user.setName(request.getParameter(USER_NAME_PARAM));
            user.setSurname(request.getParameter(USER_SURNAME_PARAM));
            user.setPatronymic(request.getParameter(USER_PATRONYMIC_PARAM));
            user.setBirthDate(Date.valueOf(request.getParameter(USER_BIRTHDATE_PARAM)));
        } catch (Exception e) {
            //LALALA
        }
        
        return user;
    }
    
    private Role formRole(HttpServletRequest request) {
        Role role = new Role();
        String[] parts = request.getParameter(USER_ROLE_PARAM).split("\\.");
        try {
            role.setId(Integer.parseInt(parts[0]));
            role.setName(parts[1]);
        } catch (NumberFormatException e) {
            LOGGER.error("Cannot parse role parameter from request", e);
        }
        return role;
    }
}
