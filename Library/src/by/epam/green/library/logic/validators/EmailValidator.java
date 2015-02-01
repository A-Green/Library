package by.epam.green.library.logic.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Class EmailValidator provides methods for e-mail address validation
 */
public class EmailValidator {

	/** Validation pattern */
	private static final String EMAIL_REGEX = 
			"^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$";

	/**
	 * Validates the email.
	 *
	 * @param email the email
	 * @return true, if successful, false otherwise
	 */
	public static boolean validateEmail(String email) {

		Pattern pattern = Pattern.compile(EMAIL_REGEX);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
}
