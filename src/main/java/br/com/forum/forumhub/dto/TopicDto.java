package br.com.forum.forumhub.dto;

import java.time.LocalDateTime;

public record TopicDto(
        Integer id,
        String title,
        String message,
        String author,
        String course,
        LocalDateTime creationDate,
        String status
) {}
