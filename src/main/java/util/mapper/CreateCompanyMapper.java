package util.mapper;

import dto.write.CreateCompanyDto;
import entity.CompanyEntity;
import lombok.Getter;

import java.util.UUID;

public class CreateCompanyMapper implements Mapper<CreateCompanyDto, CompanyEntity>{
    @Getter
    private final static CreateCompanyMapper instance = new CreateCompanyMapper();
    private final static String IMAGE_FOLDER = "companies/";

    private CreateCompanyMapper() {

    }

    @Override
    public CompanyEntity map(CreateCompanyDto object) {
        String shortUniqueString = UUID.randomUUID().toString().replaceAll("-", "");
        return CompanyEntity.builder()
                .name(object.getName())
                .imgKey(IMAGE_FOLDER + shortUniqueString + object.getImage().getSubmittedFileName())
                .build();
    }
}
