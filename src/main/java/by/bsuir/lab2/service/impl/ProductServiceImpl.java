package by.bsuir.lab2.service.impl;

import by.bsuir.lab2.bean.Locale;
import by.bsuir.lab2.bean.Product;
import by.bsuir.lab2.bean.dto.LocalizedProductTO;
import by.bsuir.lab2.bean.dto.ProductsTO;
import by.bsuir.lab2.dao.DAOFactory;
import by.bsuir.lab2.dao.DAOManager;
import by.bsuir.lab2.dao.ProductDAO;
import by.bsuir.lab2.dao.StorageType;
import by.bsuir.lab2.dao.exception.DAOException;
import by.bsuir.lab2.service.ProductService;
import by.bsuir.lab2.service.exception.ServiceException;
import by.bsuir.lab2.service.exception.ValidationException;
import by.bsuir.lab2.service.validation.Validator;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private static final DAOManager daoManager = DAOFactory.getDAOManager(StorageType.MY_SQL);
    
    private static final ProductDAO productDAO = daoManager.getProductDAO();

    @Override
    public void addProduct(LocalizedProductTO localizedProduct) throws ValidationException, ServiceException {
        if (!Validator.isProductDataValid(localizedProduct)) {
            throw new ValidationException("Sent product data isn't valid");
        }

        try {
            int productID = productDAO.addProduct(localizedProduct);
            productDAO.addLocalProductInfo(localizedProduct.getProductLocalInfoList(), productID);
        } catch (DAOException e) {
            throw new ServiceException("Error during adding new product in different locales.", e);
        }
    }

    @Override
    public Product getProduct(Locale locale, int productID) throws ServiceException {
        Product product = null;
        try {
            product = productDAO.getProduct(locale, productID);
        } catch (DAOException e) {
            throw new ServiceException("Error during getting product by id.", e);
        }
        return product;
    }

    @Override
    public ProductsTO getProductsForView(Locale locale) throws ServiceException {
        ProductsTO productsTO = null;
        try {
            List<Product> products = productDAO.getProducts(locale);
            productsTO = new ProductsTO(products);
        } catch (DAOException e) {
            throw new ServiceException("Error during getting products for view in specified locale.", e);
        }
        return productsTO;
    }
}
