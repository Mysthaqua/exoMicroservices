package tibo.spring.commandmodule.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import tibo.spring.commandmodule.dto.CommandDtoResponse;
import tibo.spring.commandmodule.dto.UserDto;
import tibo.spring.commandmodule.util.RestClient;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Command extends AbstractEntity {
    private Long userId;
    private String product;

    public CommandDtoResponse toDtoResponse() {
        RestClient<UserDto> userRestClient = new RestClient<>(String.format("http://localhost:8080/api/user/%s", userId));
        UserDto userDto = userRestClient.getRequest(UserDto.class);

        return CommandDtoResponse.builder()
                .id(getId())
                .user(userDto)
                .product(getProduct())
                .build();
    }
}