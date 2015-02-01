package by.epam.green.library.logic.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Class PhoneValidator provides methods for phone number validation
 */
public class PhoneValidator {
	
	/** Phone number pattern */
	private static final String PHONE_NUMBER_REGEX =  "(\\+)?[1-9]{1}[\\d]{7,11}$";

	/**
	 * Validate phone number.
	 *
	 * @param phone the phone number
	 * @return true, if successful, false otherwise
	 */
	public static boolean validatePhoneNumber(String phone){

		Pattern pattern = Pattern.compile(PHONE_NUMBER_REGEX);
		Matcher matcher = pattern.matcher(phone);

		return matcher.matches();
	}
	
}
