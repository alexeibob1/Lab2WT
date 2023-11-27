package by.bsuir.lab2.bean;

import java.util.Objects;

public class Manufacturer {
    private int id;
    private Locale locale;
    
    private String manufacturerName;

    public Manufacturer(Locale locale, String manufacturerName) {
        this.locale = locale;
        this.manufacturerName = manufacturerName;
    }

    public Manufacturer(int id, Locale locale, String manufacturerName) {
        this.id = id;
        this.locale = locale;
        this.manufacturerName = manufacturerName;
    }

    public Manufacturer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manufacturer that = (Manufacturer) o;
        return id == that.id && locale.equals(that.locale) && manufacturerName.equals(that.manufacturerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, locale, manufacturerName);
    }
}
