package by.bsuir.lab2.bean;

import java.math.BigDecimal;

public class Product {
    private int productID;

    private double dosage;

    private boolean isNeedPrescription;

    private int amount;

    private BigDecimal price;

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public double getDosage() {
        return dosage;
    }

    public void setDosage(double dosage) {
        this.dosage = dosage;
    }

    public boolean isNeedPrescription() {
        return isNeedPrescription;
    }

    public void setNeedPrescription(boolean needPrescription) {
        isNeedPrescription = needPrescription;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
