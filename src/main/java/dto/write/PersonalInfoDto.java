package dto.write;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PersonalInfoDto {
    private int id;
    private String name;
    private String passNo;
    private String birthday;
}
