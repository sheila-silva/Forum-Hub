package br.com.forum.forumhub.controller;

import br.com.forum.forumhub.dto.UsernamePasswordAuthenticationRequest;
import br.com.forum.forumhub.model.User;
import br.com.forum.forumhub.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody @Valid UsernamePasswordAuthenticationRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.username(), request.password())
            );

            var user = (User) authentication.getPrincipal();
            String token = tokenService.gerarToken(user);

            return ResponseEntity.ok("Bearer " + token);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).build();
        }
    }
}
