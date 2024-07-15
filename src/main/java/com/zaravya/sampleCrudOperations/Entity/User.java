package com.zaravya.sampleCrudOperations.Entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;


@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    @NotBlank(message="name  cannot be not blank")
    @NotNull(message = "name cannot be null")
    private String name;
    @NotNull(message="address cannot be null")
    private String address;
    @Email(message="email not valid enter valid email")
    @NotNull(message="email not null")
    @NotBlank(message="message cannot null")
    private String email;
    @NotNull(message = "date cannot be null")
    @DateTimeFormat(pattern = "YYYY-MM-dd")
    private LocalDate date;
}
