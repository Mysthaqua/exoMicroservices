package tibo.spring.commandmodule.dto;

import lombok.Builder;

@Builder
public record CommandDtoResponse(Long id, UserDto user, String product) {
}