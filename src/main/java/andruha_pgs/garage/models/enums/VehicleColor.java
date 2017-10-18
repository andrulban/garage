package andruha_pgs.garage.models.enums;

public enum VehicleColor {
    RED("Red"),
    WHITE("White"),
    BLACK("Black"),
    BLUE("Blue"),
    YELLOW("Yellow"),
    SILVER("Silver");


    private final String color;

    VehicleColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }


    @Override
    public String toString() {
        return color;
    }
}
