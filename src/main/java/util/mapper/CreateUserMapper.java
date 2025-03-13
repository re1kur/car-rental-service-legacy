package util.mapper;

import entity.UserEntity;
import dto.write.CreateUserDto;
import lombok.Getter;

public class CreateUserMapper implements Mapper<CreateUserDto, UserEntity> {
    @Getter
    private static final CreateUserMapper instance = new CreateUserMapper();

    private CreateUserMapper() {

    }

    @Override
    public UserEntity map(CreateUserDto object) {
        return UserEntity.builder()
                .email(object.getEmail())
                .username(object.getUsername())
                .password(object.getPassword())
                .build();
    }
}
