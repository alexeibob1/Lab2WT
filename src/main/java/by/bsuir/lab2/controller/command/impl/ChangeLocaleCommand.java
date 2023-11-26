package by.bsuir.lab2.controller.command.impl;

import by.bsuir.lab2.controller.command.Command;
import by.bsuir.lab2.controller.constant.SessionAttribute;
import by.bsuir.lab2.controller.util.UrlUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class ChangeLocaleCommand implements Command {
    private static final String LOCALE_PARAM = "locale";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String locale = request.getParameter(LOCALE_PARAM);
        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.LOCALE, locale);
        response.sendRedirect(UrlUtil.getRefererPage(request));
    }
}
