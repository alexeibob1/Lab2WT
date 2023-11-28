package by.bsuir.lab2.controller.command.impl.admin;

import by.bsuir.lab2.bean.dto.RolesTO;
import by.bsuir.lab2.bean.User;
import by.bsuir.lab2.controller.command.Command;
import by.bsuir.lab2.controller.constant.CommandName;
import by.bsuir.lab2.controller.constant.ViewPath;
import by.bsuir.lab2.service.RoleService;
import by.bsuir.lab2.service.ServiceFactory;
import by.bsuir.lab2.service.UserService;
import by.bsuir.lab2.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ShowUserCommand implements Command {
    private static final String USER_ID_PARAM = "userID";

    private static final String USER_PARAM = "user";
    private static final String ROLES_PARAM = "roles";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userID = Integer.parseInt(request.getParameter(USER_ID_PARAM));

        String viewPath = "";
        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            User user = userService.getUser(userID);
            request.setAttribute(USER_PARAM, user);
            
            RoleService roleService = ServiceFactory.getInstance().getRoleService();
            RolesTO roles = roleService.getRoles();
            request.setAttribute(ROLES_PARAM, roles);
            
            viewPath = ViewPath.COMMON_PAGES_PATH + ViewPath.FORWARD_EDIT_USER_FORM;
        } catch (ServiceException e) {
            //Logger
            viewPath += request.getContextPath() + CommandName.GO_TO_ERROR_503_COMMAND;
            response.sendRedirect(viewPath);
        }

        request.getRequestDispatcher(viewPath).forward(request, response);
    }
}
