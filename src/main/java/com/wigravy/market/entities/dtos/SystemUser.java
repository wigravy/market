package com.wigravy.market.entities.dtos;

import com.wigravy.market.utils.validation.FieldMatch;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@FieldMatch(first = "password", second = "matchingPassword", message = "The password field must match")
public class SystemUser {
    @NotNull(message = "This is a required field")
    @Size(min = 8, message = "Phone number must contain at least 8 digits")
    private String phone;

    @NotNull(message = "Password cannot be empty")
    @Size(min = 4, message = "password is too short")
    private String password;

    @NotNull(message = "Password confirmation cannot be empty")
    @Size(min = 4, message = "password is too short")
    private String matchingPassword;

    @NotNull(message = "This is a required field")
    private String firstName;

    @NotNull(message = "This is a required field")
    private String lastName;

    @NotNull(message = "This is a required field")
    @Email
    private String email;
}
