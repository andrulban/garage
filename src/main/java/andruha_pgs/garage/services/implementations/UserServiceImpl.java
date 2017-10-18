package andruha_pgs.garage.services.implementations;

import andruha_pgs.garage.exceptions.exceptions.EntityInsertIdGeneratedValueException;
import andruha_pgs.garage.models.entities.User;
import andruha_pgs.garage.models.enums.UserRole;
import andruha_pgs.garage.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService {
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findUserByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user " + userName);
        }
        return new CustomUserDetails(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User addUser(User user) throws EntityInsertIdGeneratedValueException {
        LOGGER.debug("{},  calls repository to insert", this.getClass().toString());
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setUserRole(UserRole.USER);
        if (user.getId() == 0) {
            return userRepository.save(user);
        } else {
            throw new EntityInsertIdGeneratedValueException();
        }
    }


    private final static class CustomUserDetails extends User implements UserDetails {

        private CustomUserDetails(User user) {
            super(user);
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
             if (this.getUserRole().equals(UserRole.ADMIN)){
                return AuthorityUtils.createAuthorityList("ROLE_ADMIN");
            }
             else {
                 return AuthorityUtils.createAuthorityList("ROLE_USER");
             }
        }

        @Override
        public String getUsername() {
            return getUserName();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
