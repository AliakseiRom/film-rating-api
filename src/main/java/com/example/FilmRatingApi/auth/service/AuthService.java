package com.example.FilmRatingApi.auth.service;

import com.example.FilmRatingApi.auth.dto.LoginResponse;
import com.example.FilmRatingApi.auth.dto.RegisterRequest;
import com.example.FilmRatingApi.auth.model.RoleEntity;
import com.example.FilmRatingApi.auth.model.User;
import com.example.FilmRatingApi.auth.repo.RoleRepository;
import com.example.FilmRatingApi.auth.repo.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthService {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    public AuthService(UserRepository userRepo, RoleRepository roleRepo, PasswordEncoder passwordEncoder,
                       JwtService jwtService, AuthenticationManager authManager) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authManager = authManager;
    }

    public String registerUser(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        RoleEntity roleUser = roleRepo.findByRole("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Role Not Found"));
        user.setRoles(Set.of(roleUser));

//        Set<RoleEntity> roleSet = new HashSet<>();
//        roleSet.add(new RoleEntity(user.getId(), "ROLE_USER"));
//        for (String roleName : roles) {
//            roleRepo.findByRole(roleName).ifPresent(roleSet::add);
//        }

        // Сохраняем пользователя
        userRepo.save(user);
        return "User registered successfully";
    }

    public LoginResponse loginUser(User user) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        UserDetails userDetails = userRepo.findByUsername(user.getUsername())
                .map(u -> new org.springframework.security.core.userdetails.User(
                        u.getUsername(), u.getPassword(), u.getRoles()))
                .orElseThrow();

        String token = jwtService.generateToken(userDetails);
        return new LoginResponse(token);
    }
}
