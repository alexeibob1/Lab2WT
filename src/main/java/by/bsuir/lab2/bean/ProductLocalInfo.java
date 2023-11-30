package by.bsuir.lab2.bean;

public class ProductLocalInfo {
    private int productID;

    private Locale locale;

    private String manufacturer;
    
    private String name;
    
    private String dosageUnit;
    
    private String drugForm;

    public ProductLocalInfo(int productID, Locale locale, String manufacturer, String name, String dosageUnit, String drugForm) {
        this.productID = productID;
        this.locale = locale;
        this.manufacturer = manufacturer;
        this.name = name;
        this.dosageUnit = dosageUnit;
        this.drugForm = drugForm;
    }

    public ProductLocalInfo() {
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDosageUnit() {
        return dosageUnit;
    }

    public void setDosageUnit(String dosageUnit) {
        this.dosageUnit = dosageUnit;
    }

    public String getDrugForm() {
        return drugForm;
    }

    public void setDrugForm(String drugForm) {
        this.drugForm = drugForm;
    }
}
