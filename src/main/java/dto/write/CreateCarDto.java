package dto.write;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class CreateCarDto {
    private String name;
    private int companyId;
    private LocalDate yearRelease;
}
