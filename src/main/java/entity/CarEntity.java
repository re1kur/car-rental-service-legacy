package entity;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
public class CarEntity {
    private int id;
    private String name;
    private CompanyEntity company;
    private LocalDate yearRelease;
}
