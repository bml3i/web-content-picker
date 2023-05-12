package bi.leo.picker.controller;

import bi.leo.picker.dto.AuthResponseDto;
import bi.leo.picker.dto.UserLoginDto;
import bi.leo.picker.dto.UserRegisterDto;
import bi.leo.picker.entity.Role;
import bi.leo.picker.entity.User;
import bi.leo.picker.repository.RoleRepository;
import bi.leo.picker.repository.UserRepository;
import bi.leo.picker.service.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTGenerator jwtGenerator;

    @PostMapping("register")
    public ResponseEntity<String> userRegister(@RequestBody UserRegisterDto userRegisterDto) {
        if(userRepository.existsByName(userRegisterDto.getUsername())) {
            return new ResponseEntity<>("User name already exists. ", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setName(userRegisterDto.getUsername());
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));

        Role userRole = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(userRole));
        userRepository.save(user);

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponseDto> userLogin(@RequestBody UserLoginDto userLoginDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDto.getUsername(), userLoginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtGenerator.generateToken(authentication);

        return new ResponseEntity<>(new AuthResponseDto(token), HttpStatus.OK);
    }
}
