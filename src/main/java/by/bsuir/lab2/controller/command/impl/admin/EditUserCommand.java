package by.bsuir.lab2.controller.command.impl.admin;

import by.bsuir.lab2.controller.command.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class EditUserCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(EditUserCommand.class);
    
    private static final String USER_ID_PARAM = "userID";
    private static final String USER_ROLE_PARAM = "role";
    private static final String USER_NAME_PARAM = "userID";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }
}
