package by.epam.green.library.tags;

import java.io.IOException;

import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import by.epam.green.library.model.OrderStatus;

/**
 * The Class StatusIconTag prints the icon associated with order status.
 */
public class StatusIconTag extends TagSupport {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant LOGGER. */
	public static final Logger LOGGER = Logger.getLogger(StatusIconTag.class);
	
	/** The status. */
	private String status;

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	* Prints the icon associated with order status.
	*/
	public int doStartTag() {

		String content = "";
		
		switch(OrderStatus.valueOf(status)) {
		
		case RETURNED:
			content = "<img alt=\"check\" src=\"/Library/images/check.png\" height=\"16\" width=\"16\">";
			break;
			
		case REJECTED:
			content = "<img alt=\"reject\" src=\"/Library/images/cross.png\" height=\"16\" width=\"16\">";
			break;
			
		case REQUESTED:
			content = "<img alt=\"requested\" src=\"/Library/images/clock.png\" height=\"16\" width=\"16\">";
			break;
			
		default:
			return SKIP_BODY;
		}
		
		try {
			pageContext.getOut().write(content);
		} catch (IOException e) {
			LOGGER.error("Can not write content", e);
		}
		
		return SKIP_BODY;
	}

}
