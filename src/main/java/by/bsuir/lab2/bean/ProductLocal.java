package by.bsuir.lab2.bean;

public class ProductLocal {
    private Product product;
    
    private Locale locale;

    private Manufacturer manufacturer;

    private ProductName name;

    private DosageUnit dosageUnit;

    public ProductLocal(Product product, Locale locale, Manufacturer manufacturer, ProductName name, DosageUnit dosageUnit) {
        this.product = product;
        this.locale = locale;
        this.manufacturer = manufacturer;
        this.name = name;
        this.dosageUnit = dosageUnit;
    }

    public ProductLocal() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public ProductName getName() {
        return name;
    }

    public void setName(ProductName name) {
        this.name = name;
    }

    public DosageUnit getDosageUnit() {
        return dosageUnit;
    }

    public void setDosageUnit(DosageUnit dosageUnit) {
        this.dosageUnit = dosageUnit;
    }
}
