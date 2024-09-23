package tibo.spring.usermodule.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tibo.spring.usermodule.dto.UserDtoRequest;
import tibo.spring.usermodule.dto.UserDtoResponse;
import tibo.spring.usermodule.entity.User;
import tibo.spring.usermodule.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public List<UserDtoResponse> findAllDto() {
        return findAll().stream().map(User::toDtoResponse).toList();
    }

    public User findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException(String.format("Test not found with id %s", id)));
    }

    public UserDtoResponse findDtoById(Long id) {
        return findById(id).toDtoResponse();
    }

    public List<User> findAllByName(String name) {
        return repository.findAllByName(name);
    }

    public List<UserDtoResponse> findAllDtoByName(String name) {
        return findAllByName(name).stream().map(User::toDtoResponse).toList();
    }

    @Transactional
    public UserDtoResponse create(UserDtoRequest userDtoRequest) {
        return repository.save(userDtoRequest.toEntity()).toDtoResponse();
    }

    @Transactional
    public UserDtoResponse update(Long id, UserDtoRequest userDtoRequest) {
        User user = findById(id);
        user.setName(userDtoRequest.name());
        user.setEmail(userDtoRequest.email());
        return repository.save(user).toDtoResponse();
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}