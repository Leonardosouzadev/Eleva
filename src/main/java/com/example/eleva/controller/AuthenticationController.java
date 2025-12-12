package com.example.eleva.controller;


import com.example.eleva.controller.dto.AuthenticationDTO;
import com.example.eleva.controller.dto.LoginDTO;
import com.example.eleva.controller.dto.RegisterDTO;
import com.example.eleva.entity.Profile;
import com.example.eleva.entity.Task;
import com.example.eleva.entity.User;
import com.example.eleva.entity.UserRole;
import com.example.eleva.repository.ProfileRepository;
import com.example.eleva.repository.UserRepository;
import com.example.eleva.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class    AuthenticationController {

    @Autowired
    private AuthenticationManager authenticatorManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO request) {
        var userPassword = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        var auth = this.authenticatorManager.authenticate(userPassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTO request) {
        if(this.userRepository.findByEmail(request.email()) != null) {
            return ResponseEntity.badRequest().build();
        };
        String encryptedPassword = new BCryptPasswordEncoder().encode(request.password());
        User newUser = new User(request.name(), request.email(), encryptedPassword, UserRole.USER);

        Profile newProfile = new Profile();

        newProfile.setUser(newUser);
        newUser.setProfile(newProfile);

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
