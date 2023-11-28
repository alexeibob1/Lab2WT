package by.bsuir.lab2.bean.dto;

import by.bsuir.lab2.bean.Product;

import java.util.List;

public class ProductsTO {
    //Pagination will be later
    
    private List<Product> products;

    public ProductsTO(List<Product> products) {
        this.products = products;
    }

    public ProductsTO() {
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
