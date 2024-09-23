package tibo.spring.commandmodule.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tibo.spring.commandmodule.dto.CommandDtoRequest;
import tibo.spring.commandmodule.dto.CommandDtoResponse;
import tibo.spring.commandmodule.entity.Command;
import tibo.spring.commandmodule.repository.CommandRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CommandService {
    private final CommandRepository repository;

    public CommandService(CommandRepository repository) {
        this.repository = repository;
    }

    public List<Command> findAll() {
        return repository.findAll();
    }

    public List<CommandDtoResponse> findAllDto() {
        return findAll().stream().map(Command::toDtoResponse).toList();
    }

    public Command findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException(String.format("Test not found with id %s", id)));
    }

    public CommandDtoResponse findDtoById(Long id) {
        return findById(id).toDtoResponse();
    }

    public List<Command> findAllByUserId(Long userId) {
        return repository.findAllByUserId(userId);
    }

    public List<CommandDtoResponse> findAllDtoByUserId(Long userId) {
        return findAllByUserId(userId).stream().map(Command::toDtoResponse).toList();
    }

    @Transactional
    public CommandDtoResponse create(CommandDtoRequest commandDtoRequest) {
        return repository.save(commandDtoRequest.toEntity()).toDtoResponse();
    }

    @Transactional
    public CommandDtoResponse update(Long id, CommandDtoRequest commandDtoRequest) {
        Command command = findById(id);
        command.setUserId(commandDtoRequest.userId());
        command.setProduct(commandDtoRequest.product());
        return repository.save(command).toDtoResponse();
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}