package br.com.forum.forumhub.dto;

import java.time.LocalDateTime;

public record AnswerDto(Long id, String message, LocalDateTime creationDate, String authorUsername, String topicTitle) {}