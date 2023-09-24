package com.henrry.tutorialscomments.service.impl;

import com.henrry.tutorialscomments.model.Comment;
import com.henrry.tutorialscomments.model.Tutorial;
import com.henrry.tutorialscomments.repository.CommentRepository;
import com.henrry.tutorialscomments.repository.TutorialRepository;
import com.henrry.tutorialscomments.service.TutorialService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TutorialServiceImpl implements TutorialService {
    private final CommentRepository commentRepository;
    private final TutorialRepository tutorialRepository;

    @Override
    public Tutorial create(Tutorial tutorial) {
        return this.tutorialRepository.save(tutorial);
    }

    @Override
    public Optional<Tutorial> getById(Long id) {
        return this.tutorialRepository.findById(id);
    }

    @Override
    @Transactional
    public List<Comment> getComments(Long tutorialId) {
        return this.commentRepository.findAllByTutorialId(tutorialId);
    }

    @Override
    public void delete(Long id) {
        Optional<Tutorial> tutorialFound = this.tutorialRepository.findById(id);
        if (tutorialFound.isPresent()) {
            this.tutorialRepository.deleteById(id);
        }
    }

    @Override
    public List<Tutorial> getAll() {
        List<Tutorial>getAll = this.tutorialRepository.findAll();
        return this.tutorialRepository.findAll();
    }
}
