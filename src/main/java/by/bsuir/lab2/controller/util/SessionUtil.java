package by.bsuir.lab2.controller.util;

import by.bsuir.lab2.bean.Locale;
import by.bsuir.lab2.controller.constant.SessionAttribute;
import jakarta.servlet.http.HttpSession;

public class SessionUtil {
    private static final Locale DEFAULT_LOCALE = Locale.RU;
    
    private SessionUtil() {
    }

    public static boolean isAuthenticatedUser(HttpSession session) {
        if (session.getAttribute(SessionAttribute.USER_ID) == null) {
            return false;
        }
        return true;
    }

    public static Locale getLocale(HttpSession session) {
        String localeStr = (String) session.getAttribute(SessionAttribute.LOCALE);
        Locale locale;
        if (localeStr != null) {
            locale = Locale.valueOf(localeStr.toUpperCase());
        } else {
            locale = DEFAULT_LOCALE;
        }
        return locale;
    }
}
