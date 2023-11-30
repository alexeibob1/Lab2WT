package by.bsuir.lab2.service;

import by.bsuir.lab2.bean.Locale;
import by.bsuir.lab2.bean.Product;
import by.bsuir.lab2.bean.dto.LocalizedProductTO;
import by.bsuir.lab2.bean.dto.ProductsTO;
import by.bsuir.lab2.service.exception.ServiceException;
import by.bsuir.lab2.service.exception.ValidationException;

public interface ProductService {
    void addProduct(LocalizedProductTO localizedProduct) throws ValidationException, ServiceException;

    Product getProduct(Locale locale, int productID) throws ServiceException;
    
    ProductsTO getProductsForView(Locale locale) throws ServiceException;
    
    
}
