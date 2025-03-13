package util.validator;

public interface Validator<D> {

    ValidationResult validate(D object);
}
