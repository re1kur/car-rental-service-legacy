package service;

import dao.CarDao;
import dto.write.CreateCarDto;
import entity.CarEntity;
import lombok.Getter;
import util.mapper.CreateCarMapper;

public class CarService {
    @Getter
    private static final  CarService instance = new CarService();
    private final CarDao carDao = CarDao.getInstance();
    private final CreateCarMapper createMapper = CreateCarMapper.getInstance();

    private CarService() {

    }

    public void create (CreateCarDto createCarDto) {
        CarEntity car = createMapper.map(createCarDto);
        carDao.save(car);
    }

    public int createAndReturnId(CreateCarDto createCarDto) {
        CarEntity car = createMapper.map(createCarDto);
        return carDao.saveAndReturnId(car);

    }

}
