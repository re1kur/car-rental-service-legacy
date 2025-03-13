package util.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import util.validator.ValidationError;

import java.util.List;

@Getter
@AllArgsConstructor
public class ValidationException extends RuntimeException {
    private List<ValidationError> errors;
}
