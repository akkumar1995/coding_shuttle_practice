package com.avinash.kumar.module2.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long id;
    @NotBlank(message = "name cannot be blank")
    @Size(min=3,max=11)
    private String name;
    @Email()
    private String email;
    private Integer age;
    @Past()
    private LocalDate doj;
    private Boolean isActive;
//    @Pattern(regexp = "^(user|admin)$")
//    private String role;

}
