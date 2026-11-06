package me.prathmeshsadake.linkedin.userService.controller;

import lombok.RequiredArgsConstructor;
import me.prathmeshsadake.linkedin.userService.dto.LoginRequestDto;
import me.prathmeshsadake.linkedin.userService.dto.SignupRequestDto;
import me.prathmeshsadake.linkedin.userService.dto.UserDto;
import me.prathmeshsadake.linkedin.userService.service.AuthService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignupRequestDto signupRequestDto){
            UserDto userDto = authService.signup(signupRequestDto);
            return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> signup(@RequestBody LoginRequestDto loginRequestDto){
        String token = authService.login(loginRequestDto);
        return ResponseEntity.ok(token);
    }

}
