package br.com.forum.forumhub.repository;

import br.com.forum.forumhub.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Integer> {

    Optional<Topic> findByTitleAndMessage(String title, String message);

    Page<Topic> findByCourseContainingIgnoreCase(String course, Pageable pageable);
}
