package by.bsuir.lab2.service.validation;

import by.bsuir.lab2.bean.ProductLocalInfo;
import by.bsuir.lab2.bean.User;
import by.bsuir.lab2.bean.dto.LocalizedProductTO;
import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.routines.BigDecimalValidator;

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

    public static boolean isProductDataValid(LocalizedProductTO localizedProduct) {
        if (!GenericValidator.minValue(localizedProduct.getAmount(), 0)) {
            return false;
        }
        if (!BigDecimalValidator.getInstance().minValue(localizedProduct.getPrice(), 0)) {
            return false;
        }

        for (ProductLocalInfo productInfo : localizedProduct.getProductLocalInfoList()) {
            if (GenericValidator.isBlankOrNull(productInfo.getName())) {
                return false;
            }
            if (GenericValidator.isBlankOrNull(productInfo.getManufacturer())) {
                return false;
            }
            if (GenericValidator.isBlankOrNull(productInfo.getDosageUnit())) {
                return false;
            }
            if (GenericValidator.isBlankOrNull(productInfo.getDrugForm())) {
                return false;
            }
        }

        return true;
    }
}
