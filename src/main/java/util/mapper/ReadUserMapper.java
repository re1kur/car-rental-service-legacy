package util.mapper;

import entity.UserEntity;
import dto.read.ReadUserDto;
import lombok.Getter;

public class ReadUserMapper implements Mapper<UserEntity, ReadUserDto> {
    @Getter
    private final static ReadUserMapper instance = new ReadUserMapper();

    @Override
    public ReadUserDto map(UserEntity object) {
        return ReadUserDto.builder()
                .id(object.getId())
                .email(object.getEmail())
                .username(object.getUsername())
                .role(object.getRole())
                .build();
    }

    private ReadUserMapper() {

    }
}
