package uz.pdp.apppermission.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    @NotNull(message = "username must not be empty")
    private String username;

    @NotNull(message = "password must not be empty")
    private String password;
}
