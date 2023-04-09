package com.example.activity_trackerweek8.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long userId;
    @NotBlank(message = "Enter your user-name")
    private String userName;
    @NotBlank(message = "Enter your email address")
    @Email(message = "Enter a valid email address")
    private String email;
    @NotBlank(message = "Enter your password")
    @Length(min = 8, message = "Password must be at least 8 characters")
    private String password;
}
