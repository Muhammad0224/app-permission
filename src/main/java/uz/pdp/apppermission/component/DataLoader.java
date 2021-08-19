package uz.pdp.apppermission.component;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.apppermission.domain.Role;
import uz.pdp.apppermission.domain.User;
import uz.pdp.apppermission.enums.Permission;
import uz.pdp.apppermission.repository.RoleRepo;
import uz.pdp.apppermission.repository.UserRepo;
import uz.pdp.apppermission.utils.AppConstants;

import java.util.Arrays;

import static uz.pdp.apppermission.enums.Permission.*;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final UserRepo userRepo;

    private final RoleRepo roleRepo;

    private final PasswordEncoder passwordEncoder;

    @Value("${spring.datasource.initialization-mode}")
    private String initialMode;

    @Override
    public void run(String... args) throws Exception {
        if (initialMode.equals("always")) {
            Role admin = roleRepo.save(new Role(
                    AppConstants.ADMIN,
                    Arrays.asList(values()),
                    "Admin"
            ));

            Role user = roleRepo.save(new Role(
                    AppConstants.USER,
                    Arrays.asList(
                            ADD_COMMENT,
                            EDIT_COMMENT,
                            DELETE_MY_COMMENT),
                    "User"
            ));

            userRepo.save(new User(
                    "Admin",
                    "admin",
                    passwordEncoder.encode("7777"),
                    admin,
                    true
            ));

            userRepo.save(new User(
                    "User",
                    "user",
                    passwordEncoder.encode("1111"),
                    user,
                    true
            ));
        }


    }
}
