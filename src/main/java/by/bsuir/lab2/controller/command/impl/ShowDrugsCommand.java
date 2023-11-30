package by.bsuir.lab2.controller.command.impl;

import by.bsuir.lab2.bean.Locale;
import by.bsuir.lab2.bean.dto.ProductsTO;
import by.bsuir.lab2.controller.command.Command;
import by.bsuir.lab2.controller.constant.ViewPath;
import by.bsuir.lab2.controller.util.SessionUtil;
import by.bsuir.lab2.service.ProductService;
import by.bsuir.lab2.service.ServiceFactory;
import by.bsuir.lab2.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class ShowDrugsCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ShowDrugsCommand.class);

    private static final String PRODUCTS_VIEW_ATTR = "productsView";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Locale locale = SessionUtil.getLocale(session);
        String viewPath = "";
        try {
            ProductService productService = ServiceFactory.getInstance().getProductService();
            ProductsTO productsTo = productService.getProductsForView(locale);
            request.setAttribute(PRODUCTS_VIEW_ATTR, productsTo);
            viewPath = ViewPath.COMMON_PAGES_PATH + ViewPath.FORWARD_PRODUCTS;
        } catch (ServiceException e) {
            LOGGER.error("Exception during getting info about products.", e);
            viewPath = ViewPath.COMMON_PAGES_PATH + ViewPath.REDIRECT_503;
        }
        
        request.getRequestDispatcher(viewPath).forward(request, response);
    }
}
