package tibo.spring.commandmodule.dto;

import lombok.Builder;
import tibo.spring.commandmodule.entity.Command;

@Builder
public record CommandDtoRequest(Long userId, String product) {
    public Command toEntity() {
        return Command.builder()
                .userId(userId)
                .product(product)
                .build();
    }

    public static CommandDtoRequest empty() {
        return new CommandDtoRequest(0L, "");
    }
}