package dto.write;

import jakarta.servlet.http.Part;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateRentalDto {
    private CreateCarDto car;
    private Part image;
    private int price;
    private String description;
    private int ownerId;
}
