package by.bsuir.lab2.controller.util;

import jakarta.servlet.http.HttpServletRequest;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.utils.URIBuilder;

public class UrlUtil {
    //Logger

    private static final String REFERER_HEADER = "referer";
    
    private UrlUtil() {
    }

    public static String getRefererPage(HttpServletRequest request) {
        String refererPage = request.getHeader(REFERER_HEADER);
        return refererPage;
    }

    public static URI addParameterToUri(String uri, String paramName, String paramValue) {
        try {
            URI uriWithParam = new URIBuilder(uri).setParameter(paramName, paramValue).build();
            return uriWithParam;
        } catch (URISyntaxException e) {
            //Logger
            throw new RuntimeException(e);
        }
    }

    public static URI addParameterToRefererPage(HttpServletRequest request, String paramName, String paramValue) {
        URI uriWithParam = addParameterToUri(getRefererPage(request), paramName, paramValue);
        return uriWithParam;
    }
}