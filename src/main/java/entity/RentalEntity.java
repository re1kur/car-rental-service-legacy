package entity;

import lombok.*;

@Data
@Builder
public class RentalEntity {
    private int id;
    private int ownerId;
    private String imgKey;
    private CarEntity car;
    private int price;
    private String description;
}
