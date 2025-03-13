package service;

import dao.UserDao;
import entity.UserEntity;
import dto.write.CreateUserDto;
import dto.read.ReadUserDto;
import util.exception.ValidationException;
import lombok.Getter;
import util.mapper.CreateUserMapper;
import util.mapper.ReadUserMapper;
import util.validator.CreateUserValidator;
import util.validator.ValidationResult;

import java.util.Optional;

public class UserService {
    @Getter
    private static final UserService instance = new UserService();
    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserValidator createValidator = CreateUserValidator.getINSTANCE();
    private final CreateUserMapper createMapper = CreateUserMapper.getInstance();
    private final ReadUserMapper readMapper = ReadUserMapper.getInstance();

    private UserService() {
    }

    public Optional<ReadUserDto> read(String email, String password) {
        Optional<UserEntity> maybeUser = userDao.findByEmailAndPassword(email, password);
        return maybeUser.map(readMapper::map);
    }

    public void create(CreateUserDto userDto) {
        // VALIDATION
        ValidationResult result = createValidator.validate(userDto);
        if (!result.isValid()) throw new ValidationException(result.getErrors());
        // MAPPING
        UserEntity user = createMapper.map(userDto);
        //SAVING
        userDao.save(user);
    }



}
