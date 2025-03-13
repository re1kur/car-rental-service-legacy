package entity;

import lombok.*;

@Data
@Builder
public class UserEntity {
    private int id;
    private String email;
    private String username;
    private String password;
    private String role;
}
