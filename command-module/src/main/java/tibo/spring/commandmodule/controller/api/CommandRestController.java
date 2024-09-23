package tibo.spring.commandmodule.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tibo.spring.commandmodule.dto.CommandDtoRequest;
import tibo.spring.commandmodule.dto.CommandDtoResponse;
import tibo.spring.commandmodule.service.CommandService;

import java.util.List;

@RestController
@RequestMapping("/api/command")
public class CommandRestController {
    private final CommandService service;

    public CommandRestController(CommandService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CommandDtoResponse>> getAll(@RequestParam(required = false) Long userId) {
        return ResponseEntity.ok(userId == null ? service.findAllDto() : service.findAllDtoByUserId(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommandDtoResponse> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findDtoById(id));
    }

    @PostMapping
    public ResponseEntity<CommandDtoResponse> create(@Validated @RequestBody CommandDtoRequest commandDtoRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(commandDtoRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommandDtoResponse> update(@PathVariable("id") Long id, @Validated @RequestBody CommandDtoRequest commandDtoRequest) {
        return ResponseEntity.ok(service.update(id, commandDtoRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}