package by.bsuir.lab2.controller.util;

import java.sql.Date;

public class DateUtil {
    private DateUtil() {
    }
    
    public static Date getDateFromString(String strDate) {
        if (!strDate.isBlank()) {
            return Date.valueOf(strDate);
        };
        
        return null;
    }
}
