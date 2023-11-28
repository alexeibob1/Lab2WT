package by.bsuir.lab2.bean;

public class DosageUnit {
    private int dosageUnitID;
    
    private Locale locale;
    
    private String dosageName;

    public DosageUnit(int dosageUnitID, Locale locale, String dosageName) {
        this.dosageUnitID = dosageUnitID;
        this.locale = locale;
        this.dosageName = dosageName;
    }

    public DosageUnit() {
    }

    public int getDosageUnitID() {
        return dosageUnitID;
    }

    public void setDosageUnitID(int dosageUnitID) {
        this.dosageUnitID = dosageUnitID;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getDosageName() {
        return dosageName;
    }

    public void setDosageName(String dosageName) {
        this.dosageName = dosageName;
    }
}
