package dto.read;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ReadCompanyDto {
    int id;
    String name;
    String imgKey;
}
