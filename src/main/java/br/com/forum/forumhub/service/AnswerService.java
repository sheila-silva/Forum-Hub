package br.com.forum.forumhub.service;

import br.com.forum.forumhub.dto.*;
import br.com.forum.forumhub.model.Answer;
import br.com.forum.forumhub.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository repository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    public AnswerDto create(CreateAnswerRequest request) {
        Answer answer = new Answer();
        answer.setMessage(request.message());
        answer.setCreationDate(LocalDateTime.now());
        answer.setTopic(topicRepository.findById(request.topicId())
                .orElseThrow(() -> new RuntimeException("Topic not found")));
        answer.setAuthor(userRepository.findById(request.authorId())
                .orElseThrow(() -> new RuntimeException("Author not found")));
        return toDto(repository.save(answer));
    }

    public List<AnswerDto> list() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    public AnswerDto getById(Long id) {
        return repository.findById(id).map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Answer not found"));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private AnswerDto toDto(Answer a) {
        return new AnswerDto(a.getId(), a.getMessage(), a.getCreationDate(),
                a.getAuthor().getUsername(), a.getTopic().getTitle());
    }
}