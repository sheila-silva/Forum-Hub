package br.com.forum.forumhub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateAnswerRequest(
        @NotBlank String message,
        @NotNull Integer topicId,
        @NotNull Long authorId
) {}
