package andruha_pgs.garage.models.enums;

public enum FuelType {
    GASOLINE("Gasoline"),
    DIESEL("Diesel"),
    PROPANE("Liquefied Petroleum"),
    ETHANOL("Ethanol"),
    BIO_DIESEL("Bio-diesel");


    private final String value;

    FuelType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


    @Override
    public String toString() {
        return value;
    }
}
