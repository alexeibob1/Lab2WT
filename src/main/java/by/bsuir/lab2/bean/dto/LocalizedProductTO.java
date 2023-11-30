package by.bsuir.lab2.bean.dto;

import by.bsuir.lab2.bean.ProductLocalInfo;

import java.math.BigDecimal;
import java.util.List;

public class LocalizedProductTO {
    private int productID;

    private double dosage;

    private boolean isNeedPrescription;

    private int amount;

    private BigDecimal price;
    
    private List<ProductLocalInfo> productLocalInfoList;

    public LocalizedProductTO() {
    }

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

    public boolean getIsNeedPrescription() {
        return isNeedPrescription;
    }

    public void setIsNeedPrescription(boolean needPrescription) {
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

    public List<ProductLocalInfo> getProductLocalInfoList() {
        return productLocalInfoList;
    }

    public void setProductLocalInfoList(List<ProductLocalInfo> productLocalInfoList) {
        this.productLocalInfoList = productLocalInfoList;
    }
}
