package util.mapper;

import dto.read.ReadCompanyDto;
import entity.CompanyEntity;
import lombok.Getter;

public class ReadCompanyMapper implements Mapper<CompanyEntity, ReadCompanyDto> {
    @Getter
    private final static ReadCompanyMapper instance = new ReadCompanyMapper();

    private ReadCompanyMapper() {

    }

    @Override
    public ReadCompanyDto map(CompanyEntity object) {
        return ReadCompanyDto.builder()
                .id(object.getId())
                .imgKey(object.getImgKey())
                .name(object.getName())
                .build();
    }
}
