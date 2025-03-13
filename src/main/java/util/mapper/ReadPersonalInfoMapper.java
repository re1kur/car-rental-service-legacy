package util.mapper;

import entity.PersonalInfoEntity;
import dto.write.PersonalInfoDto;
import lombok.Getter;

public class ReadPersonalInfoMapper implements Mapper<PersonalInfoEntity, PersonalInfoDto> {
    @Getter
    private final static ReadPersonalInfoMapper instance = new ReadPersonalInfoMapper();

    private ReadPersonalInfoMapper() {

    }

    @Override
    public PersonalInfoDto map(PersonalInfoEntity object) {
        return PersonalInfoDto.builder()
                .id(object.getId())
                .name(object.getName())
                .passNo(object.getPassNo())
                .birthday(String.valueOf(object.getBirthday()))
                .build();
    }
}
