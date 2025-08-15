package service;

import dao.PersonalInfoDao;
import entity.PersonalInfoEntity;
import dto.write.PersonalInfoDto;
import dto.read.ReadUserDto;
import lombok.Getter;
import util.mapper.CreatePersonalInfoMapper;
import util.mapper.ReadPersonalInfoMapper;

import java.util.Optional;

public class PersonalInfoService {
    @Getter
    private static final PersonalInfoService instance = new PersonalInfoService();
    private final ReadPersonalInfoMapper readMapper = ReadPersonalInfoMapper.getInstance();
    private final PersonalInfoDao personalInfoDao = PersonalInfoDao.getInstance();
    private final CreatePersonalInfoMapper createMapper = CreatePersonalInfoMapper.getInstance();


    private PersonalInfoService() {

    }

    public Optional<PersonalInfoDto> read(ReadUserDto user) {
        Optional<PersonalInfoEntity> maybePersonalInfo = personalInfoDao.findById(user.getId());
        return maybePersonalInfo.map(readMapper::map);
    }

    public void create(PersonalInfoDto personalInfoDto) {
        PersonalInfoEntity personalInfo = createMapper.map(personalInfoDto);
        personalInfoDao.save(personalInfo);
    }
}
