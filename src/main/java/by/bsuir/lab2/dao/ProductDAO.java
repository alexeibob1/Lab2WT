package by.bsuir.lab2.dao;

import by.bsuir.lab2.bean.Locale;
import by.bsuir.lab2.bean.Product;
import by.bsuir.lab2.bean.ProductLocalInfo;
import by.bsuir.lab2.bean.dto.LocalizedProductTO;
import by.bsuir.lab2.dao.exception.DAOException;

import java.util.List;

public interface ProductDAO {
    
    int addProduct(LocalizedProductTO localizedProductTO) throws DAOException;
    
    void addLocalProductInfo(List<ProductLocalInfo> productLocalInfoList, int productID) throws DAOException;
    
    Product getProduct(Locale locale, int productID) throws DAOException;
    
    List<Product> getProducts(Locale locale) throws DAOException;
    
    void updateProduct(Product product) throws DAOException;
}
