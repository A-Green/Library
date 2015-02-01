package by.epam.green.library.tags;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

/**
 * The Class ReturnDateReminderTag provides information of
 * days overdue or days, that left for returning granted books. 
 * Uses for orders with status GRANTED.
 */
public class ReturnDateReminderTag extends TagSupport {

	/** The Constant LOGGER. */
	public static final Logger LOGGER = Logger.getLogger(ReturnDateReminderTag.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The date formatter. */
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	/** The return date. */
	private String returndate;

	/**
	 * Sets the return date.
	 *
	 * @param returnDate the new return date
	 */
	public void setReturndate(String returnDate) {
		this.returndate = returnDate;
	}
	
	/**
	* Prints amount of days, that left for book returning
	*/
	public int doStartTag() {
		
		String content = "";
			
			String[] locale = ((String) pageContext.getSession().getAttribute("locale")).split("_");
	        Locale current = new Locale(locale[0], locale[1]); 

	        ResourceBundle bundle= ResourceBundle.getBundle("properties.pagecontent", current);
			Date returnDate;
			
			try {
				returnDate = dateFormat.parse(returndate);
			} catch (ParseException e) {
				LOGGER.error("Can not parse date", e);
				return SKIP_BODY;
			}
			
			long days = (TimeUnit.MILLISECONDS.toDays(returnDate.getTime() - new Date().getTime()));
			
			if (days >=0) {
				content = days + " " + bundle.getString("label.days_left");
			} else {
				content = -days + " " + bundle.getString("label.days_overdue");
			}
		
		try {
			pageContext.getOut().write(content);
		} catch (IOException e) {
			LOGGER.error("Can not write content", e);
		}
		
		return SKIP_BODY;
	}
}
