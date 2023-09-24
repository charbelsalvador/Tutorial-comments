package com.henrry.tutorialscomments.repository;

import com.henrry.tutorialscomments.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByTutorialId(Long tutorialId);
}
