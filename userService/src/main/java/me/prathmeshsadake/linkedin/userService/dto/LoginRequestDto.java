package me.prathmeshsadake.linkedin.userService.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email, password;
}
