package com.example.FilmRatingApi.auth.config;

import com.example.FilmRatingApi.auth.model.RoleEntity;
import com.example.FilmRatingApi.auth.model.User;
import com.example.FilmRatingApi.auth.repo.RoleRepository;
import com.example.FilmRatingApi.auth.repo.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.example.FilmRatingApi.auth.model.Role.ROLE_ADMIN;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initRoles(
            RoleRepository roleRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {
            if (roleRepository.count() == 0) {
                RoleEntity userRole = new RoleEntity();
                userRole.setRole("ROLE_USER");

                RoleEntity adminRole = new RoleEntity();
                adminRole.setRole("ROLE_ADMIN");

                roleRepository.saveAll(List.of(userRole, adminRole));

                System.out.println("Roles initialized.");
            }

            RoleEntity adminRole = new RoleEntity();
            adminRole.setRole("ROLE_ADMIN");

            Optional<User> existingUser = userRepository.findByUsername("user1");
            if (existingUser.isEmpty()) {
                User admin = new User();
                admin.setUsername("user1");
                admin.setPassword(passwordEncoder.encode("pass123"));
                admin.setRoles(Set.of(adminRole));
                userRepository.save(admin);

                System.out.println("Admin user created: user1/pass123");
            }
        };
    }
}