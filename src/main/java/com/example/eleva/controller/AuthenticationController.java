package com.example.eleva.controller;


import com.example.eleva.controller.dto.AuthenticatorDTO;
import com.example.eleva.controller.dto.RegisterDTO;
import com.example.eleva.entity.User;
import com.example.eleva.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticatorManager;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticatorDTO request) {
        var userPassword = new UsernamePasswordAuthenticationToken(request.login(), request.password());
        var auth = this.authenticatorManager.authenticate(userPassword);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTO request) {
        if(this.userRepository.findByEmail(request.email()) != null) {
            return ResponseEntity.badRequest().build();
        };
        String encryptedPassword = new BCryptPasswordEncoder().encode(request.password());
        User newUser = new User(request.name(), request.email(), encryptedPassword, request.role());

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
