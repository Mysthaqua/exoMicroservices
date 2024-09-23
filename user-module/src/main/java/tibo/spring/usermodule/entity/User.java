package tibo.spring.usermodule.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import tibo.spring.usermodule.dto.UserDtoResponse;

@Entity(name = "user_account")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class User extends AbstractEntity {
    private String name;
    private String email;

    public UserDtoResponse toDtoResponse() {
        return UserDtoResponse.builder()
                .id(getId())
                .name(getName())
                .email(getEmail())
                .build();
    }
}