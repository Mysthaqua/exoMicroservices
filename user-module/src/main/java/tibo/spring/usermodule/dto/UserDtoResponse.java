package tibo.spring.usermodule.dto;

import lombok.Builder;

@Builder
public record UserDtoResponse(Long id, String name, String email) {
}