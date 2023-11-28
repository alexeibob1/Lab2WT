package by.bsuir.lab2.bean.dto;

import by.bsuir.lab2.bean.Manufacturer;

import java.util.List;

public class ManufacturersTO {
    private List<Manufacturer> manufacturers;

    public ManufacturersTO() {
    }

    public List<Manufacturer> getManufacturers() {
        return manufacturers;
    }

    public void setManufacturers(List<Manufacturer> manufacturers) {
        this.manufacturers = manufacturers;
    }
}
