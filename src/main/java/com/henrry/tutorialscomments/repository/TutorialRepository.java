package com.henrry.tutorialscomments.repository;

import com.henrry.tutorialscomments.model.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
}
