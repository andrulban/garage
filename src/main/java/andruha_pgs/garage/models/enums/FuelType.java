package andruha_pgs.garage.models.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum FuelType {
    @JsonProperty("gasoline")
    GASOLINE("gasoline"),
    @JsonProperty("diesel")
    DIESEL("diesel"),
    @JsonProperty("propane")
    PROPANE("propane"),
    @JsonProperty("ethanol")
    ETHANOL("ethanol"),
    @JsonProperty("bio-diesel")
    BIO_DIESEL("bio-diesel");


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
