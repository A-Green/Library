package by.epam.green.library.logic.dateparser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Class DateParser.
 */
public class DateParser {
	
	/** The date format. */
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * Parses the date.
	 *
	 * @param strDate the String date
	 * @return the date
	 * @throws ParseException the parse exception
	 */
	public static Date parseDate(String strDate) throws ParseException{
		return dateFormat.parse(strDate);
	}
}
