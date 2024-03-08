package com.energygame.mcqapplication.Repository;

import com.energygame.mcqapplication.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
