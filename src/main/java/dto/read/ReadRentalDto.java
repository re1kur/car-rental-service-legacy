package dto.read;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
@Builder
public class ReadRentalDto {
    private int id;
    private int ownerId;
    private String imgKey;
    private String companyName;
    private String carName;
    private LocalDate yearRelease;
    private int price;
    private String description;
}
