package by.bsuir.lab2.controller.util;

import by.bsuir.lab2.controller.command.Command;
import jakarta.servlet.http.HttpServletRequest;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.utils.URIBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UrlUtil {
    public static final Logger LOGGER = LogManager.getLogger(Command.class);

    private static final String REFERER_HEADER = "referer";
    
    private UrlUtil() {
    }

    public static String getRefererPage(HttpServletRequest request) {
        String refererPage = request.getHeader(REFERER_HEADER);
        return refererPage;
    }

    public static URI addParameterToUri(String uri, String paramName, String paramValue) {
        try {
            return new URIBuilder(uri).setParameter(paramName, paramValue).build();
        } catch (URISyntaxException e) {
            LOGGER.error("Exception during parsing URI string", e);
            throw new RuntimeException(e);
        }
    }

    public static URI addParameterToRefererPage(HttpServletRequest request, String paramName, String paramValue) {
        return addParameterToUri(getRefererPage(request), paramName, paramValue);
    }
}