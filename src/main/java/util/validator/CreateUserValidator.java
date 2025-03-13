package util.validator;

import dto.write.CreateUserDto;
import lombok.Getter;


public class CreateUserValidator implements Validator<CreateUserDto>{
    @Getter
    private static final CreateUserValidator INSTANCE = new CreateUserValidator();

    private CreateUserValidator() {

    }

    @Override
    public ValidationResult validate(CreateUserDto object) {
        ValidationResult result = new ValidationResult();
        if (object.getEmail().length() > 128) {
            result.addError(ValidationError.of("invalid.email",
                    "The email must be lesser than 128 chars."));
        }
        if (object.getUsername().length() > 64)
            result.addError(ValidationError.of("invalid.username",
                    "The username must be less than 64 chars."));
        if (object.getPassword().length() > 128) {
            result.addError(ValidationError.of("invalid.password",
                    "The password must be lesser than 128 chars."));
        }
        return result;
    }
}
