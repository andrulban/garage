package andruha_pgs.garage.models.entities;

import andruha_pgs.garage.views.View;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
@Table(indexes = {@Index(columnList = "fullName")})
public class Owner {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    @JsonView(value = {View.Vehicle.class, View.Owner.class})
    private Long id;
    @Column(nullable = false, length = 100)
    @NotNull
    @Size(max = 100, message = "Maximum 100 symbols.")
    @JsonView(value = {View.Vehicle.class, View.Owner.class})
    private String fullName;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonView(value = {View.Vehicle.class, View.Owner.class})
    private Date dateOfBirth;
    @Version
    @Column(nullable = false)
    @JsonView(value = {View.Vehicle.class, View.Owner.class})
    private int version;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vehicle__owner", joinColumns = @JoinColumn(name = "owner_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "vehicle_id", referencedColumnName = "id"))
    @JsonView(value = {View.Owner.class})
    private Set<Vehicle> vehicles;


    public Owner() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Set<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Set<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
