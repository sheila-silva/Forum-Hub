package br.com.forum.forumhub.service;

import br.com.forum.forumhub.dto.CreateTopicRequest;
import br.com.forum.forumhub.dto.TopicDto;
import br.com.forum.forumhub.dto.UpdateTopicRequest;
import br.com.forum.forumhub.model.Topic;
import br.com.forum.forumhub.model.TopicStatus;
import br.com.forum.forumhub.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // <-- use essa importação

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Transactional
    public TopicDto create(CreateTopicRequest data) {
        topicRepository.findByTitleAndMessage(data.getTitle(), data.getMessage())
                .ifPresent(t -> {
                    throw new RuntimeException("Tópico com mesmo título e mensagem já existe.");
                });

        Topic topic = new Topic();
        topic.setTitle(data.getTitle());
        topic.setMessage(data.getMessage());
        topic.setAuthor(data.getAuthor());
        topic.setCourse(data.getCourse());
        topic.setCreationDate(LocalDateTime.now());
        topic.setStatus(TopicStatus.UNANSWERED);

        Topic saved = topicRepository.save(topic);
        return toDto(saved);
    }

    public Page<TopicDto> list(String course, Integer year, Pageable pageable) {
        Page<Topic> page;

        if (course != null && year != null) {
            List<Topic> filtered = topicRepository.findAll(pageable)
                    .getContent().stream()
                    .filter(t -> t.getCreationDate().getYear() == year &&
                            t.getCourse().equalsIgnoreCase(course))
                    .toList();

            page = new PageImpl<>(filtered, pageable, filtered.size());
        } else if (course != null) {
            page = topicRepository.findByCourseContainingIgnoreCase(course, pageable);
        } else {
            page = topicRepository.findAll(pageable);
        }

        return page.map(this::toDto);
    }

    public TopicDto getById(Integer id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico com ID " + id + " não encontrado."));
        return toDto(topic);
    }

    @Transactional
    public TopicDto update(Integer id, UpdateTopicRequest data) {
        Optional<Topic> optionalTopic = topicRepository.findById(id);
        if (optionalTopic.isEmpty()) {
            throw new RuntimeException("Tópico com ID " + id + " não encontrado.");
        }

        topicRepository.findByTitleAndMessage(data.getTitle(), data.getMessage())
                .ifPresent(existing -> {
                    if (!existing.getId().equals(id)) {
                        throw new RuntimeException("Já existe outro tópico com o mesmo título e mensagem.");
                    }
                });

        Topic topic = optionalTopic.get();
        topic.setTitle(data.getTitle());
        topic.setMessage(data.getMessage());
        topic.setAuthor(data.getAuthor());
        topic.setCourse(data.getCourse());

        Topic updated = topicRepository.save(topic);
        return toDto(updated);
    }

    @Transactional
    public void delete(Integer id) {
        Optional<Topic> optionalTopic = topicRepository.findById(id);
        if (optionalTopic.isEmpty()) {
            throw new RuntimeException("Tópico com ID " + id + " não encontrado.");
        }
        topicRepository.deleteById(id);
    }

    private TopicDto toDto(Topic topic) {
        return new TopicDto(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getAuthor(),
                topic.getCourse(),
                topic.getCreationDate(),
                topic.getStatus().name()
        );
    }
}
