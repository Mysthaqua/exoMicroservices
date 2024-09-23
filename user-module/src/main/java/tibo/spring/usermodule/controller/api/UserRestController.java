package tibo.spring.usermodule.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tibo.spring.usermodule.dto.UserDtoRequest;
import tibo.spring.usermodule.dto.UserDtoResponse;
import tibo.spring.usermodule.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
    private final UserService service;

    public UserRestController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UserDtoResponse>> getAll(@RequestParam(required = false) String name) {
        return ResponseEntity.ok(name == null ? service.findAllDto() : service.findAllDtoByName(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDtoResponse> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findDtoById(id));
    }

    @PostMapping
    public ResponseEntity<UserDtoResponse> create(@Validated @RequestBody UserDtoRequest userDtoRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(userDtoRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDtoResponse> update(@PathVariable("id") Long id, @Validated @RequestBody UserDtoRequest userDtoRequest) {
        return ResponseEntity.ok(service.update(id, userDtoRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}