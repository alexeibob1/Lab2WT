package by.bsuir.lab2.controller.command.impl.admin;

import by.bsuir.lab2.bean.UsersTO;
import by.bsuir.lab2.controller.command.Command;
import by.bsuir.lab2.controller.constant.ViewPath;
import by.bsuir.lab2.service.ServiceFactory;
import by.bsuir.lab2.service.UserService;
import by.bsuir.lab2.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class ShowUsersCommand implements Command {
    private static final String USERS_VIEW_PARAM = "usersView";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String viewPath = "";
        
        try {
            UserService userService = ServiceFactory.getInstance().getUserService();
            UsersTO usersTO = userService.getUsersForView();
            request.setAttribute(USERS_VIEW_PARAM, usersTO);
            viewPath = ViewPath.COMMON_PAGES_PATH + ViewPath.FORWARD_USERS_EDITOR;
        } catch (ServiceException e) {
            //Logger
            viewPath = ViewPath.COMMON_PAGES_PATH + ViewPath.REDIRECT_503;
        }
        
        request.getRequestDispatcher(viewPath).forward(request, response);
    }
}