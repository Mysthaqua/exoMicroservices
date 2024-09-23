package tibo.spring.usermodule.dto;

import lombok.Builder;
import tibo.spring.usermodule.entity.User;

@Builder
public record UserDtoRequest(String name, String email) {
    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .build();
    }

    public static UserDtoRequest empty() {
        return new UserDtoRequest("", "");
    }
}