package andruha_pgs.garage.models.enums;

public enum VehicleBodyType {
    SEDAN("Sedan"),
    HATCHBACK("HatchBack"),
    UNIVERSAL("Universal"),
    COUPE("Coupe"),
    CABRIOLET("Cabriolet"),
    MINIVAN("Minivan"),
    PICKUP("Pickup"),
    OFFROAD_4X4("4x4");



    private final String value;

    VehicleBodyType(String value) {
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
