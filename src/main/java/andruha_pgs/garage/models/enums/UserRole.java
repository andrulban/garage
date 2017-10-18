package andruha_pgs.garage.models.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum UserRole {

    @JsonProperty("user")
    USER("user"),
    @JsonProperty("admin")
    ADMIN("admin");

    private final String value;

    UserRole(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public String getValue() {
        return value;
    }
}
