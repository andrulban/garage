package andruha_pgs.garage.models.entities;

import andruha_pgs.garage.models.enums.BodyType;
import andruha_pgs.garage.models.enums.Color;
import andruha_pgs.garage.models.enums.FuelType;
import andruha_pgs.garage.views.View;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(indexes = {@Index(columnList = "brand"), @Index(columnList = "bodyType")})
public class Vehicle {
    @Id
    @Column(nullable = false, length = 10, name = "id")
    @NotNull
    @Size(max = 10, message = "Maximum 10 symbols.")
    @JsonView(value = {View.Vehicle.class, View.Owner.class})
    private String numberPlate;
    @Column(nullable = false)
    @NotNull
    @Min(value = 500, message = "Minimum weight is 500 kg.")
    @JsonView(value = {View.Vehicle.class, View.Owner.class})
    private int weightKg;
    @Column(nullable = false, length = 25)
    @NotNull
    @Size(max = 25, message = "Maximum 25 symbols.")
    @JsonView(value = {View.Vehicle.class, View.Owner.class})
    private String brand;
    @Column(nullable = false, length = 25)
    @NotNull
    @Size(max = 25, message = "Maximum 25 symbols.")
    @JsonView(value = {View.Vehicle.class, View.Owner.class})
    private String brandLine;
    @Column(nullable = false)
    @NotNull
    @Min(value = 1800, message = "The minimum year is 1800.")
    @Max(value = 2040, message = "The maximum year is 2040.")
    @JsonView(value = {View.Vehicle.class, View.Owner.class})
    private int year;
    @Column(nullable = false)
    @NotNull
    @Min(value = 900, message = "Minimum engine capacity is 900 cm3.")
    @Max(value = 20000, message = "Maximum engine capacity is 20000 cm3.")
    @JsonView(value = {View.Vehicle.class, View.Owner.class})
    private int engineCapacityCm3;
    @Column(nullable = false)
    @NotNull
    @Max(value = 999999, message = "Maximum mileage is 999 999 km.")
    @JsonView(value = {View.Vehicle.class, View.Owner.class})
    private int mileage;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    @NotNull
    @JsonView(value = {View.Vehicle.class, View.Owner.class})
    private Color color;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    @NotNull
    @JsonView(value = {View.Vehicle.class, View.Owner.class})
    private BodyType bodyType;
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, length = 30)
    @NotNull
    @JsonView(value = {View.Vehicle.class, View.Owner.class})
    private FuelType fuelType;
    @Version
    @Column(nullable = false)
    @JsonView(value = {View.Vehicle.class, View.Owner.class})
    private int version;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vehicle__owner", joinColumns = @JoinColumn(name = "vehicle_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "owner_id", referencedColumnName = "id"))
    @JsonView(value = {View.Vehicle.class})
    private Set<Owner> owners;

    public Vehicle() {
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrandLine() {
        return brandLine;
    }

    public void setBrandLine(String brandLine) {
        this.brandLine = brandLine;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public int getEngineCapacityCm3() {
        return engineCapacityCm3;
    }

    public void setEngineCapacityCm3(int engineCapacityCm3) {
        this.engineCapacityCm3 = engineCapacityCm3;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Set<Owner> getOwners() {
        return owners;
    }

    public void setOwners(Set<Owner> owners) {
        this.owners = owners;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }

    public int getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(int weightKg) {
        this.weightKg = weightKg;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vehicle vehicle = (Vehicle) o;

        return numberPlate.equals(vehicle.numberPlate);
    }

    @Override
    public int hashCode() {
        return numberPlate.hashCode();
    }
}
