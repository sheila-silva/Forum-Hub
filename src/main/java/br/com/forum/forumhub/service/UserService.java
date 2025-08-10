package br.com.forum.forumhub.service;

import br.com.forum.forumhub.dto.*;
import br.com.forum.forumhub.model.User;
import br.com.forum.forumhub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserDto create(CreateUserRequest request) {
        User user = new User();
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole("ROLE_USER");
        return toDto(repository.save(user));
    }

    public List<UserDto> list() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    public UserDto getById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return toDto(user);
    }

    public UserDto update(Long id, UpdateUserRequest request) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        return toDto(repository.save(user));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getUsername());
    }
}
