package andruha_pgs.garage.models.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum BodyType {
    @JsonProperty("sedan")
    SEDAN("sedan"),
    @JsonProperty("hatchback")
    HATCHBACK("hatchback"),
    @JsonProperty("universal")
    UNIVERSAL("universal"),
    @JsonProperty("coupe")
    COUPE("coupe"),
    @JsonProperty("cabriolet")
    CABRIOLET("cabriolet"),
    @JsonProperty("minivan")
    MINIVAN("minivan"),
    @JsonProperty("pickup")
    PICKUP("pickup"),
    @JsonProperty("4x4")
    OFFROAD_4X4("4x4");



    private final String value;

    BodyType(String value) {
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
