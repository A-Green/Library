package by.epam.green.library.commands;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.green.library.logic.OrderOptions;
import by.epam.green.library.logic.exception.TechnicalException;
import by.epam.green.library.managers.ResourceManager;
import by.epam.green.library.model.ClientType;
import by.epam.green.library.model.Order;

/**
 * The Class ShowOrdersCommand.
 */
public class ShowOrdersCommand implements Command{

	/** The Constant LOGGER. */
	static final Logger LOGGER = Logger.getLogger(ShowOrdersCommand.class);

	/**
	* {@inheritDoc}
	*/
	@Override
	public String execute(HttpServletRequest request) {
		
		String page = null;	
		String clientId = null;
		String role = request.getSession().getAttribute("access").toString();
		
		switch (ClientType.valueOf(role)) {
		
		case LIBRARIAN:
			
			clientId = request.getParameter("readerId");
			
			if (clientId != null) {
				clientId = request.getParameter("readerId");
				request.getSession().setAttribute("readerId", clientId);
			} else {
				clientId = (String) request.getSession().getAttribute("readerId");
			}

			page = ResourceManager.getInstance().getParametr("reader_orders_control_page");
			break;

		case READER:
			clientId = request.getSession().getAttribute("clientId").toString();
			page = ResourceManager.getInstance().getParametr("orders_reader_page");
			break;
			
		default:
			LOGGER.error("Unknown client role.");
			return ResourceManager.getInstance().getParametr("authorization_page");
		}
		
		ArrayList<Order> orders = new ArrayList<Order>();
		String status = request.getParameter("ordersStatus");
		OrderOptions options = new OrderOptions();	
		
		try {
			orders = options.showReaderOrders(Integer.parseInt(clientId), status);
		} catch (TechnicalException e) {
			LOGGER.error("Can not show reader orders", e);
			return ResourceManager.getInstance().getParametr("error_page");
		}
		
		request.setAttribute("Orders", orders);
	
		return page;
	}
}
