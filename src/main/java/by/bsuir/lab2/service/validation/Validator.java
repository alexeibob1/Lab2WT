package by.bsuir.lab2.service.validation;

import by.bsuir.lab2.bean.User;
import org.apache.commons.validator.GenericValidator;

import java.sql.Date;

public class Validator {
    public static boolean isRegistrationDataValid(User user) {
        String email = user.getEmail();
        String login = user.getUsername();
        String password = user.getPassword();
        String name = user.getName();
        String surname = user.getSurname();
        Date birthDate = user.getBirthDate();

        if (!GenericValidator.isEmail(email)) {
            return false;
        }
        if (GenericValidator.isBlankOrNull(login) || GenericValidator.isBlankOrNull(name) || GenericValidator.isBlankOrNull(surname)
                || GenericValidator.isBlankOrNull(birthDate.toString())) {
            return false;
        }
        if (GenericValidator.isBlankOrNull(password) || !GenericValidator.minLength(password, 7)) {
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
