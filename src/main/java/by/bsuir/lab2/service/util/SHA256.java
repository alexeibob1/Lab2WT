package by.bsuir.lab2.service.util;

import org.apache.commons.codec.digest.DigestUtils;
public class SHA256 {
    public static String getSHA256Hash(String s) {
        return DigestUtils.sha256Hex(s);
    }
}
