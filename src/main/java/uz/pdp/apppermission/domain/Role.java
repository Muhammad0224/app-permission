package uz.pdp.apppermission.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import uz.pdp.apppermission.domain.templ.GenericEntity;
import uz.pdp.apppermission.enums.Permission;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Role extends GenericEntity {
    @Column(nullable = false, unique = true)
    private String name;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Permission> permissions;

    @Column(columnDefinition = "text")
    private String description;
}
