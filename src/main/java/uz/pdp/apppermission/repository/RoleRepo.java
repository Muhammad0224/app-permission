package uz.pdp.apppermission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.apppermission.domain.Role;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);
}
