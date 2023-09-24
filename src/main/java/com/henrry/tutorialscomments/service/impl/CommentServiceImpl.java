package com.henrry.tutorialscomments.service.impl;

import com.henrry.tutorialscomments.model.Comment;
import com.henrry.tutorialscomments.model.Tutorial;
import com.henrry.tutorialscomments.repository.CommentRepository;
import com.henrry.tutorialscomments.repository.TutorialRepository;
import com.henrry.tutorialscomments.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final TutorialRepository tutorialRepository;

    @Override
    public Comment create(Comment comment, Long tutorialId) {
        Optional<Tutorial> tutorialFound = this.tutorialRepository.findById(tutorialId);
        if(tutorialFound.isEmpty()) {
            return null;
        }

        comment.setTutorial(tutorialFound.get());
        return this.commentRepository.save(comment);
    }

    @Override
    public Optional<Comment> getById(Long commentId) {
        return this.commentRepository.findById(commentId);
    }

    @Override
    public void delete(Long commentId) {
        Optional<Comment> commentFound = this.commentRepository.findById(commentId);
        if(commentFound.isPresent()) {
            this.commentRepository.deleteById(commentId);
        }
    }

    @Override
    public List<Comment> getAll() {
        return this.commentRepository.findAll();
    }

    @Override
    public Optional<Comment> updateComment(String content, Long commentId) {
        Optional<Comment>commentUpdate = this.commentRepository.findById(commentId);
        if (commentUpdate.isPresent()){
            Comment comment = commentUpdate.get();
            comment.setContent(content);
            return Optional.of(this.commentRepository.save(comment));
        }
        return Optional.empty();

    }
}
