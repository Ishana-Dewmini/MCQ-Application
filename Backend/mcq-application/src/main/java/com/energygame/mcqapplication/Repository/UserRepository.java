package com.energygame.mcqapplication.Repository;

import com.energygame.mcqapplication.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByJwtToken(String jwtToken);
}
