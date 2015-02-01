package by.epam.green.library.commands;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.green.library.logic.CatalogOptions;
import by.epam.green.library.logic.OrderOptions;
import by.epam.green.library.logic.exception.TechnicalException;
import by.epam.green.library.managers.ResourceManager;
import by.epam.green.library.model.Book;
import by.epam.green.library.model.OrderType;

/**
 * The Class OrderBookCommand.
 */
public class OrderBookCommand implements Command {
	
	/** The Constant LOGGER. */
	static final Logger LOGGER = Logger.getLogger(OrderBookCommand.class);

	/**
	* {@inheritDoc}
	*/
	@Override
	public String execute(HttpServletRequest request) {
		
		String[] selected = request.getParameterValues("selected");

		if (selected == null || selected.length == 0) {
			return ResourceManager.getInstance().getParametr("catalog_librarian_page");
		}
		
		OrderOptions orderOptions = new OrderOptions();
		CatalogOptions catalogOptions = new CatalogOptions();
		
		int clientId = Integer.parseInt(request.getSession().getAttribute("clientId").toString());
		OrderType orderType = OrderType.valueOf(request.getParameter("ordersType"));
		
		for (int i = 0; i < selected.length; i++) {
		
			int bookId = Integer.parseInt(selected[i]);
			
			try {
				orderOptions.orderBook(clientId, bookId, orderType);
			} catch (TechnicalException e) {
				LOGGER.error("Can not order book", e);
				return ResourceManager.getInstance().getParametr("error_page");
			}
		}
		
		ArrayList<Book> books = new ArrayList<Book>();	
		
		try {
			books = catalogOptions.showAll();
		} catch (TechnicalException e) {
			LOGGER.error("Can not prepare catalog", e);
			return ResourceManager.getInstance().getParametr("error_page");
		}
		
		request.setAttribute("Books", books);	
		
		return ResourceManager.getInstance().getParametr("catalog_reader_page");
	}

}
