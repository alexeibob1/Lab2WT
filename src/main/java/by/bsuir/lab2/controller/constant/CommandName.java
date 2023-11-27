package by.bsuir.lab2.controller.constant;

public class CommandName {
    public static final String GO_TO_LOGIN_PAGE_COMMAND = "/login-form";

    public static final String GO_TO_REGISTRATION_PAGE_COMMAND = "/register-form";

    public static final String REGISTER_COMMAND = "/register";
    
    public static final String LOGIN_COMMAND = "/login";
    
    public static final String LOGOUT_COMMAND = "/logout";

    public static final String DEFAULT_COMMAND = "/";

    public static final String GO_TO_HOME_COMMAND = "/home";

    public static final String GO_TO_ERROR_503_COMMAND = "/error503";

    public static final String GO_TO_ERROR_404_COMMAND = "/error404";

    public static final String CHANGE_LOCALE_COMMAND = "/change-locale";

    public static final String UNKNOWN_COMMAND = "/error404";
    
    //ADMIN
    
    public static final String GO_TO_DRUGS_EDITOR_COMMAND = "/admin/drugs";
    
    public static final String GO_TO_USERS_EDITOR_COMMAND = "/admin/users";
    
    public static final String SHOW_USER_COMMAND = "/admin/users/show";
}
