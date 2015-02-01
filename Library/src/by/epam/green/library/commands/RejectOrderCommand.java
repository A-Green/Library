package by.epam.green.library.commands;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.green.library.logic.OrderOptions;
import by.epam.green.library.logic.exception.TechnicalException;
import by.epam.green.library.managers.ResourceManager;
import by.epam.green.library.model.Order;

/**
 * The Class RejectOrderCommand.
 */
public class RejectOrderCommand implements Command {
	
	/** The Constant LOGGER. */
	static final Logger LOGGER = Logger.getLogger(RejectOrderCommand.class);

	/**
	* {@inheritDoc}
	*/
	@Override
	public String execute(HttpServletRequest request) {
		
		//ids of orders to reject
		String[] selected = request.getParameterValues("selected");

		OrderOptions options = new OrderOptions();

		for (int i = 0; i < selected.length; i++) {
			int orderId = Integer.parseInt(selected[i].replaceFirst(";(\\d)*", ""));
			
			try {
				options.reject(orderId);
			} catch (TechnicalException e) {
				LOGGER.error("Can not reject order", e);
				return ResourceManager.getInstance().getParametr("error_page");
			} 
		}
		
		ArrayList<Order> orders = new ArrayList<Order>();

		String status = request.getParameter("OrderStatus");
		String clientId = request.getSession().getAttribute("readerId").toString();
		
		try {
			orders = options.showReaderOrders(Integer.valueOf(clientId), status);
		} catch (TechnicalException e) {
			LOGGER.error("Can not show reader orders", e);
			return ResourceManager.getInstance().getParametr("error_page");
		}
		
		request.setAttribute("Orders", orders);
		
		return ResourceManager.getInstance().getParametr("reader_orders_control_page");
	}

}
