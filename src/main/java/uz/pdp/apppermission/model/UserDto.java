package uz.pdp.apppermission.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NotNull(message = "full name must not be empty")
    private String fullName;

    @NotNull(message = "username must not be empty")
    private String username;

    @NotNull(message = "password must not be empty")
    private String password;

    @NotNull(message = "role id must not be empty")
    private Long roleId;
}
