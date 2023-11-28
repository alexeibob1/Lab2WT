package by.bsuir.lab2.bean;

public class BasketItem {
    private int clientID;
    
    private int productID;
    
    private int amount;

    public BasketItem(int clientID, int productID, int amount) {
        this.clientID = clientID;
        this.productID = productID;
        this.amount = amount;
    }

    public BasketItem() {
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
