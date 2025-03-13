package dto.write;

import jakarta.servlet.http.Part;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateCompanyDto {
    String name;
    Part image;
}
