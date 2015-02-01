package by.epam.green.library.commands;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.green.library.logic.OrderOptions;
import by.epam.green.library.logic.exception.TechnicalException;
import by.epam.green.library.managers.ResourceManager;
import by.epam.green.library.model.Order;

/**
 * The Class ReturnBooksCommand.
 */
public class ReturnBooksCommand implements Command {
	
	/** The Constant LOGGER. */
	static final Logger LOGGER = Logger.getLogger(ReturnBooksCommand.class);

	/**
	* {@inheritDoc}
	*/
	@Override
	public String execute(HttpServletRequest request) {
		
		//ids of books to return and orders
		String[] selected = request.getParameterValues("selected");

		OrderOptions options = new OrderOptions();

		for (int i = 0; i < selected.length; i++) {

			String[] ids = selected[i].split(";");
			int orderId = Integer.parseInt(ids[0]);
			int bookId = Integer.parseInt(ids[1]);

			try {
				options.returnBook(orderId, bookId);
			} catch (TechnicalException e) {
				LOGGER.error("Can not grant book", e);
				return ResourceManager.getInstance().getParametr("error_page");
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
