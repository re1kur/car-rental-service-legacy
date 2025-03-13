package util.mapper;

import dto.read.ReadRentalDto;
import entity.RentalEntity;
import lombok.Getter;

public class ReadRentalMapper implements Mapper<RentalEntity, ReadRentalDto> {
    @Getter
    private static final ReadRentalMapper instance = new ReadRentalMapper();

    private ReadRentalMapper() {

    }

    @Override
    public ReadRentalDto map(RentalEntity object) {
        return ReadRentalDto.builder()
                .id(object.getId())
                .ownerId(object.getOwnerId())
                .companyName(object.getCar().getCompany().getName())
                .carName(object.getCar().getName())
                .imgKey(object.getImgKey())
                .yearRelease(object.getCar().getYearRelease())
                .price(object.getPrice())
                .description(object.getDescription())
                .build();
    }
}
