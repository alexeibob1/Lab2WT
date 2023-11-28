package by.bsuir.lab2.bean;

public class ProductName {
    private int productNameID;
    
    private Locale locale;
    
    private int name;

    public ProductName(int productNameID, Locale locale, int name) {
        this.productNameID = productNameID;
        this.locale = locale;
        this.name = name;
    }

    public ProductName() {
    }

    public int getProductNameID() {
        return productNameID;
    }

    public void setProductNameID(int productNameID) {
        this.productNameID = productNameID;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }
}
