package uz.pdp.apppermission.model.resp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import uz.pdp.apppermission.domain.Role;
import uz.pdp.apppermission.domain.User;
import uz.pdp.apppermission.domain.templ.GenericEntity;
import uz.pdp.apppermission.enums.Permission;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRespDto {
    private Long id;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private String createdBy;

    private String updatedBy;

    private String fullName;

    private String username;

    private String roleName;

    private boolean enabled = false;
}
