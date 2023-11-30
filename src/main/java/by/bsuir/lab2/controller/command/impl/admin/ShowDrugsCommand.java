package by.bsuir.lab2.controller.command.impl.admin;

import by.bsuir.lab2.controller.command.Command;
import by.bsuir.lab2.controller.constant.ViewPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ShowDrugsCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(ViewPath.COMMON_PAGES_PATH + ViewPath.REDIRECT_DRUGS_EDITOR).forward(request, response);
    }
}
