package util.mapper;

import dto.write.CreateRentalDto;
import entity.RentalEntity;
import lombok.Getter;

import java.util.UUID;


public class CreateRentalMapper implements Mapper<CreateRentalDto, RentalEntity>{
    @Getter
    private final static CreateRentalMapper instance = new CreateRentalMapper();
    private static final String IMAGE_FOLDER = "project-init/rentals/";
    private final CreateCarMapper carMapper = CreateCarMapper.getInstance();

    private CreateRentalMapper() {
    }

    public RentalEntity map(CreateRentalDto object) {
        String shortUniqueString = UUID.randomUUID().toString().replaceAll("-", "");
        return RentalEntity.builder()
                .price(object.getPrice())
                .description(object.getDescription())
                .ownerId(object.getOwnerId())
                .car(carMapper.map(object.getCar()))
                .imgKey(IMAGE_FOLDER + shortUniqueString + object.getImage().getSubmittedFileName())
                .build();
    }
}
