package example.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.validator.routines.EmailValidator;

public class ValidEmailValidator implements ConstraintValidator<ValidEmailAddress, String> {

	private int min;

	@Override
	public void initialize(ValidEmailAddress validEmailAddress) {
		min = validEmailAddress.min();
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {

		if (email.length() == 0) {
			return true;
		} else if (email.length() < min) {
			return false;
		}

		if (!EmailValidator.getInstance(false).isValid(email)) {
			return false;
		}

		return true;
	}

}
