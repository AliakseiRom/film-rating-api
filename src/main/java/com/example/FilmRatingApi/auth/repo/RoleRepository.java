package com.example.FilmRatingApi.auth.repo;

import com.example.FilmRatingApi.auth.model.Role;
import com.example.FilmRatingApi.auth.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByRole(String role);
}
