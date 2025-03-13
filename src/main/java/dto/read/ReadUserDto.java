package dto.read;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReadUserDto {
    private int id;
    private String email;
    private String username;
    private String role;
}
