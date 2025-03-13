package util.mapper;

import entity.PersonalInfoEntity;
import dto.write.PersonalInfoDto;
import lombok.Getter;

import java.time.LocalDate;

public class CreatePersonalInfoMapper implements Mapper<PersonalInfoDto, PersonalInfoEntity> {
    @Getter
    private static final CreatePersonalInfoMapper instance = new CreatePersonalInfoMapper();

    @Override
    public PersonalInfoEntity map(PersonalInfoDto object) {
        return PersonalInfoEntity.builder()
                .id(object.getId())
                .name(object.getName())
                .passNo(object.getPassNo())
                .birthday(LocalDate.parse(object.getBirthday()))
                .build();
    }
}
