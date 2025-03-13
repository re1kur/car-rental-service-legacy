package entity;

import lombok.*;

@Data
@Builder
public class CompanyEntity {
    private int id;
    private String name;
    private String imgKey;
}
