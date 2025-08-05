package br.com.forum.forumhub.controller;

import br.com.forum.forumhub.dto.CreateTopicRequest;
import br.com.forum.forumhub.dto.TopicDto;
import br.com.forum.forumhub.dto.UpdateTopicRequest;
import br.com.forum.forumhub.service.TopicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @PostMapping
    public ResponseEntity<TopicDto> create(@RequestBody @Valid CreateTopicRequest request) {
        TopicDto created = topicService.create(request);
        URI location = URI.create("/topicos/" + created.id());
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping
    public ResponseEntity<Page<TopicDto>> list(
            @RequestParam(required = false) String course,
            @RequestParam(required = false) Integer year,
            @PageableDefault(size = 10, sort = "creationDate", direction = org.springframework.data.domain.Sort.Direction.ASC)
            Pageable pageable
    ) {
        Page<TopicDto> topics = topicService.list(course, year, pageable);
        return ResponseEntity.ok(topics);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDto> getById(@PathVariable Integer id) {
        TopicDto topic = topicService.getById(id);
        return ResponseEntity.ok(topic);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicDto> update(
            @PathVariable Integer id,
            @RequestBody @Valid UpdateTopicRequest request
    ) {
        TopicDto updated = topicService.update(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        topicService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
