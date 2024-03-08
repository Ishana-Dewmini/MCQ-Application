package com.energygame.mcqapplication.Repository;

import com.energygame.mcqapplication.Model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
