package uz.pdp.apppermission.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.pdp.apppermission.domain.User;
import uz.pdp.apppermission.exceptions.ForbiddenException;

@Component
@Aspect
public class CheckPermissionExecutor {
    @Before(value = "@annotation(checkPermission)")
    public void checkUserPermissionMyMethod(CheckPermission checkPermission) {
        String permission = checkPermission.permission();
        boolean exist = false;
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        for (GrantedAuthority authority : user.getAuthorities()) {
            if (authority.getAuthority().equals(permission)) {
                exist = true;
                break;
            }
        }
        if (!exist)
            throw new ForbiddenException(permission, "Not allowed");
    }
}
