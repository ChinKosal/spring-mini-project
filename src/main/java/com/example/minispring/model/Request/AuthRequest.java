package com.example.minispring.model.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
   @NotBlank
   @NotNull
   @Email
   private String email;
   @NotBlank
   @NotNull
   @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z0-9\\d]{8,}$")
   private String password;
}
