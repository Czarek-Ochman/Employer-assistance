package pl.employer.assistance.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserDto {

    @JsonIgnore
    private long id;

    @Email
    @NotBlank
    @NotNull(message = "E-mail is mandatory")
    private String email;

    @NotNull
    @NotBlank(message = "Password is mandatory")
    private String password;
}