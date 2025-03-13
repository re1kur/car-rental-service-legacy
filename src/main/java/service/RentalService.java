package service;

import dao.RentalDao;
import dto.write.CreateRentalDto;
import dto.read.ReadRentalDto;
import entity.RentalEntity;
import lombok.Getter;
import lombok.SneakyThrows;
import util.exception.ValidationException;
import util.mapper.CreateRentalMapper;
import util.mapper.ReadRentalMapper;
import util.validator.CreateRentalValidator;
import util.validator.ValidationResult;

import java.util.List;
import java.util.Optional;

public class RentalService {
    @Getter
    private static final RentalService instance = new RentalService();
    private final RentalDao rentalDao = RentalDao.getInstance();
    private final ReadRentalMapper readMapper = ReadRentalMapper.getInstance();
    private final CreateRentalMapper createMapper = CreateRentalMapper.getInstance();
    private final CreateRentalValidator rentalValidator = CreateRentalValidator.getInstance();
    private final ImageService imageService = ImageService.getInstance();



    private RentalService() {
    }

    public List<ReadRentalDto> readAll() {
        List<RentalEntity> rentals = rentalDao.findAll();
        return rentals.stream().map(readMapper::map).toList();
    }

    public List<ReadRentalDto> readByCompanyId(int companyId) {
        List<RentalEntity> companies = rentalDao.findByCompanyId(companyId);
        return companies.stream().map(readMapper::map).toList();
    }

    @SneakyThrows
    public void create(CreateRentalDto createRentalDto) {
        ValidationResult validationResult = rentalValidator.validate(createRentalDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        RentalEntity mapped = createMapper.map(createRentalDto);
        imageService.upload(mapped.getImgKey(), createRentalDto.getImage().getInputStream());
        rentalDao.save(mapped);
    }

    public Optional<ReadRentalDto> read(int id) {
        return rentalDao.findById(id).map(readMapper::map);
    }
}
