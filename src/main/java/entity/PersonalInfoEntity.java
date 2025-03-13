package entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PersonalInfoEntity {
    private int id;
    private String name;
    private String passNo;
    private LocalDate birthday;
}
