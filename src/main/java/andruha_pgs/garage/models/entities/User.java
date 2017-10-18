package andruha_pgs.garage.models.entities;

import andruha_pgs.garage.models.enums.UserRole;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "authentication", uniqueConstraints = {@UniqueConstraint(name = "UK_email", columnNames = "email"),
        @UniqueConstraint(name = "UK_user_name", columnNames = "user_name")})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name = "user_name", nullable = false, unique = true, length = 40)
    @Size(min = 3, max = 40, message = "Username length from 3 to 40 symbols")
    private String userName;
    @Column(name = "password", nullable = false, length = 100)
    @Size(min = 8, max = 100, message = "Password length from 8 to 100 symbols")
    private String password;
    @Column(name = "email", nullable = false, unique = true, length = 100)
    @Email(message = "Email is incorrect")
    @Size(min = 5, max = 100, message = "Min 5, max 100 symbols")
    private String email;
    @Column(nullable = false, length = 10)
    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;

    public User() {
    }

    public User(User user) {
        this.id = user.id;
        this.userName = user.userName;
        this.password = user.password;
        this.email = user.email;
        this.userRole = user.userRole;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
