package util.mapper;

import dao.CompanyDao;
import dto.write.CreateCarDto;
import entity.CarEntity;
import entity.CompanyEntity;
import lombok.Getter;

import java.util.Optional;

public class CreateCarMapper implements Mapper<CreateCarDto, CarEntity>{
    @Getter
    private static final CreateCarMapper instance = new CreateCarMapper();
    private final CompanyDao companyDao = CompanyDao.getInstance();

    private CreateCarMapper() {

    }

    @Override
    public CarEntity map(CreateCarDto object) {
        Optional<CompanyEntity> maybeCompanyEntity = companyDao.findById(object.getCompanyId());
        return CarEntity.builder()
                .name(object.getName())
                .yearRelease(object.getYearRelease())
                .company(maybeCompanyEntity.orElse(null))
                .build();
    }
}
