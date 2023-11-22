package by.bsuir.lab2.controller.command;

import by.bsuir.lab2.controller.command.impl.ChangeLocaleCommand;
import by.bsuir.lab2.controller.command.impl.GoToCommand;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.bsuir.lab2.controller.constant.CommandName.*;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    //private static final Logger LOGGER = LogManager.getLogger(CommandFactory.class);
    private static final Map<String, Command> commands = new HashMap<>();
    private static final CommandFactory instance = new CommandFactory();

    private CommandFactory() {
        commands.put(GO_TO_LOGIN_PAGE_COMMAND, new GoToCommand("login.jsp"));
        commands.put(GO_TO_REGISTRATION_PAGE_COMMAND, new GoToCommand("registration.jsp"));
        commands.put(DEFAULT_COMMAND, new GoToCommand("home.jsp"));
        commands.put(GO_TO_HOME_COMMAND, new GoToCommand("home.jsp"));
        commands.put(GO_TO_ERROR_503_COMMAND, new GoToCommand("error503.jsp"));
        commands.put(GO_TO_ERROR_404_COMMAND, new GoToCommand("error404.jsp"));
        commands.put(CHANGE_LOCALE_COMMAND, new ChangeLocaleCommand());
    }

    public Command getCommand(HttpServletRequest request) {
        String commandName = request.getServletPath();
        Command command = commands.get(commandName);
        if (null == command) {
            return commands.get(GO_TO_ERROR_404_COMMAND);
        }
        return command;
    }

    public static CommandFactory getInstance() {
        return instance;
    }
}
