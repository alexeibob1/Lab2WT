package by.bsuir.lab2.controller.command;

import by.bsuir.lab2.bean.Role;
import by.bsuir.lab2.controller.constant.SessionAttribute;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static by.bsuir.lab2.controller.constant.CommandName.GO_TO_HOME_COMMAND;

public class LogoutCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute(SessionAttribute.ROLE);
        session.removeAttribute(SessionAttribute.USER_ID);
        session.removeAttribute(SessionAttribute.USERNAME);
        response.sendRedirect(request.getContextPath() + GO_TO_HOME_COMMAND);
    }
}
