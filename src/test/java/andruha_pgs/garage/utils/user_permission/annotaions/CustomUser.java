package andruha_pgs.garage.utils.user_permission.annotaions;

import andruha_pgs.garage.models.enums.UserRole;
import andruha_pgs.garage.utils.user_permission.factories.CustomUserSecurityContextFactory;
import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.*;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@WithSecurityContext(factory = CustomUserSecurityContextFactory.class)
public @interface CustomUser {
    String userName() default "user";
    String email() default "user@gmail.com";
    UserRole userRole() default UserRole.USER;
    long id() default 0L;
}
