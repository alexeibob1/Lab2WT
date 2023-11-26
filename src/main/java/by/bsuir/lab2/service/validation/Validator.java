package by.bsuir.lab2.service.validation;

import by.bsuir.lab2.bean.User;
import org.apache.commons.validator.GenericValidator;

public class Validator {
    public static boolean isRegistrationDataValid(User user) {
        String email = user.getEmail();
        String login = user.getUsername();
        String password = user.getPassword();

        if (!GenericValidator.isEmail(email)) {
            return false;
        }
        if (GenericValidator.isBlankOrNull(login)) {
            return false;
        }
        if (GenericValidator.isBlankOrNull(password) || !GenericValidator.minLength(password, 6)) {
            return false;
        }
        return true;
    }
    
    public static boolean isLoginDataValid(String usernameOrEmail, String password) {
        if (GenericValidator.isBlankOrNull(usernameOrEmail)) {
            return false;
        }
        if (GenericValidator.isBlankOrNull(password)) {
            return false;
        }
        return true;
    }
}
