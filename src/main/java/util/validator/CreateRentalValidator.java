package util.validator;

import dto.write.CreateRentalDto;
import lombok.Getter;

public class CreateRentalValidator implements Validator<CreateRentalDto>{
    @Getter
    private final static CreateRentalValidator instance = new CreateRentalValidator();
    private final CreateCarValidator carValidator = CreateCarValidator.getInstance();

    private CreateRentalValidator() {

    }
    @Override
    public ValidationResult validate(CreateRentalDto object) {
        ValidationResult result = new ValidationResult();
        if (object.getDescription().length() > 256) result.addError(
                ValidationError.of("invalid.rental.description",
                        "The description must be lesser than 256 characters.")
        );
        if (object.getPrice() < 0) result.addError(ValidationError.of("invalid.rental.price",
                "The price must be greater than 0."));
        carValidator.validate(object.getCar()).getErrors().forEach(result::addError);
        return result;
    }
}
