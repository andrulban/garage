package andruha_pgs.garage.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

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
    private Long id;
    @Column(nullable = false, length = 100)
    @NotNull
    @Size(max = 100, message = "Maximum 100 symbols.")
    private String fullName;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateOfBirth;
    @Version
    @Column(nullable = false)
    private int version;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vehicle__owner", joinColumns = @JoinColumn(name = "owner_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "vehicle_id", referencedColumnName = "id"))
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
}
