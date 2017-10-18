package andruha_pgs.garage.utils.user_permission.factories;

import andruha_pgs.garage.models.entities.User;
import andruha_pgs.garage.utils.user_permission.annotaions.CustomUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class CustomUserSecurityContextFactory implements WithSecurityContextFactory<CustomUser> {

    @Override
    public SecurityContext createSecurityContext(CustomUser customUser) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        User principal = new User();
        principal.setUserName(customUser.userName());
        principal.setEmail(customUser.email());
        principal.setId(customUser.id());
        principal.setUserRole(customUser.userRole());
        Authentication auth = new UsernamePasswordAuthenticationToken(principal, "password", AuthorityUtils.createAuthorityList("ROLE_USER"));
        context.setAuthentication(auth);
        return context;
    }
}
