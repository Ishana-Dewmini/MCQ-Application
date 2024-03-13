package com.energygame.mcqapplication.Repository;

import com.energygame.mcqapplication.Model.Response;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResponseRepository extends JpaRepository<Response, Long> {
    List<Response> findByUserUserId(Long userId);
}
