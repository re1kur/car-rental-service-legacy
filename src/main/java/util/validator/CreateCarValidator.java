package util.validator;

import dto.write.CreateCarDto;
import lombok.Getter;

import java.time.LocalDate;

public class CreateCarValidator implements Validator<CreateCarDto> {
    @Getter
    private final static CreateCarValidator instance = new CreateCarValidator();

    private CreateCarValidator() {
    }

    @Override
    public ValidationResult validate(CreateCarDto object) {
        ValidationResult result = new ValidationResult();
        if (object.getName().length() > 64) result.addError(ValidationError.of("invalid.car.name",
                "Name of car must be lesser than 64 characters."));
        if (object.getYearRelease().isAfter(LocalDate.now())) result.addError(ValidationError.of("invalid.car.yearRelease",
                "Year of release mustn't be after than now."));
        return result;
    }
}
