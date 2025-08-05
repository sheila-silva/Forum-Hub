package br.com.forum.forumhub.dto;

import jakarta.validation.constraints.NotBlank;

public record UsernamePasswordAuthenticationRequest(
        @NotBlank String username,
        @NotBlank String password
) {}
