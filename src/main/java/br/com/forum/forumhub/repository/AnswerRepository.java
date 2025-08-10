package br.com.forum.forumhub.repository;

import br.com.forum.forumhub.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {}