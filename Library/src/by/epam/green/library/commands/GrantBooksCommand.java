package by.epam.green.library.commands;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.green.library.logic.OrderOptions;
import by.epam.green.library.logic.dateparser.DateParser;
import by.epam.green.library.logic.exception.LogicException;
import by.epam.green.library.logic.exception.TechnicalException;
import by.epam.green.library.managers.ResourceManager;
import by.epam.green.library.model.Order;

/**
 * The Class GrantBooksCommand.
 */
public class GrantBooksCommand implements Command {

	/** The Constant LOGGER. */
	static final Logger LOGGER = Logger.getLogger(GrantBooksCommand.class);

	/**
	* {@inheritDoc}
	*/
	@Override
	public String execute(HttpServletRequest request) {

		//ids of books to grant and orders
		String[] selected = request.getParameterValues("selected");

		OrderOptions options = new OrderOptions();
		Date returnDate = null;

		try {
			returnDate = DateParser.parseDate(request.getParameter("date"));
		} catch (ParseException e2) {
			LOGGER.warn("uncorrect return date. 30 days setted");
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, 30);
			returnDate = calendar.getTime();
		}

		for (int i = 0; i < selected.length; i++) {

			String[] ids = selected[i].split(";");
			int orderId = Integer.parseInt(ids[0]);
			int bookId = Integer.parseInt(ids[1]);

			try {
				options.grant(orderId, bookId, returnDate);
			} catch (TechnicalException e) {
				LOGGER.error("Can not grant book", e);
				return ResourceManager.getInstance().getParametr("error_page");
			} catch (LogicException e) {
				request.setAttribute("warnMessage", true);
			}

		}
		
		ArrayList<Order> orders = new ArrayList<Order>();

		String status = request.getParameter("OrderStatus");
		String clientId = request.getSession().getAttribute("readerId").toString();
		
		try {
			orders = options.showReaderOrders(Integer.parseInt(clientId), status);
		} catch (TechnicalException e) {
			LOGGER.error("Can not show reader orders", e);
			return ResourceManager.getInstance().getParametr("error_page");
		}
		
		request.setAttribute("Orders", orders);
		
		return ResourceManager.getInstance().getParametr("reader_orders_control_page");
	}

}
