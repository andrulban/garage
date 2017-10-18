package andruha_pgs.garage.models.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Color {
    @JsonProperty("red")
    RED("red"),
    @JsonProperty("white")
    WHITE("white"),
    @JsonProperty("black")
    BLACK("black"),
    @JsonProperty("blue")
    BLUE("blue"),
    @JsonProperty("yellow")
    YELLOW("yellow"),
    @JsonProperty("silver")
    SILVER("silver");


    private final String color;

    Color(String color) {
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
