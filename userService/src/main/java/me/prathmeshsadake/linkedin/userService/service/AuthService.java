package me.prathmeshsadake.linkedin.userService.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.prathmeshsadake.linkedin.userService.dto.LoginRequestDto;
import me.prathmeshsadake.linkedin.userService.dto.SignupRequestDto;
import me.prathmeshsadake.linkedin.userService.dto.UserDto;
import me.prathmeshsadake.linkedin.userService.entity.User;
import me.prathmeshsadake.linkedin.userService.exception.BadRequestException;
import me.prathmeshsadake.linkedin.userService.exception.ResourceNotFoundException;
import me.prathmeshsadake.linkedin.userService.repository.UserRepository;
import me.prathmeshsadake.linkedin.userService.utils.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;


    public UserDto signup(SignupRequestDto signupRequestDto){
        boolean exists = userRepository.existsByEmail(signupRequestDto.getEmail());

        if(exists){
            throw new BadRequestException("User already exists");
        }

        User user = modelMapper.map(signupRequestDto, User.class);
        user.setPassword(BCrypt.hash(signupRequestDto.getPassword()));
        user = userRepository.save(user);
        return modelMapper.map(user, UserDto.class);

    }

    public String login(LoginRequestDto loginRequestDto){
        User user = userRepository
                .findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid credentials"));

        boolean isPasswordMatched = BCrypt.match(loginRequestDto.getPassword(), user.getPassword());
        if (!isPasswordMatched) {
            throw new BadRequestException("Invalid credentials");
        }
        return jwtService.generateToken(user);
    }
}
