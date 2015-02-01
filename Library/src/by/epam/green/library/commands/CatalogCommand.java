package by.epam.green.library.commands;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.green.library.logic.CatalogOptions;
import by.epam.green.library.logic.exception.TechnicalException;
import by.epam.green.library.managers.ResourceManager;
import by.epam.green.library.model.Book;
import by.epam.green.library.model.ClientType;

/**
 * The Class CatalogCommand.
 */
public class CatalogCommand implements Command {
	
	/** The Constant LOGGER. */
	static final Logger LOGGER = Logger.getLogger(CatalogCommand.class);

	/**
	* {@inheritDoc} 
	* 
	* @return catalog page or error page if technical error occurred
	*/
	@Override
	public String execute(HttpServletRequest request) {
		
		String page = null;
		ArrayList<Book> books = new ArrayList<Book>();
		CatalogOptions catalog = new CatalogOptions();
		
		try {
			books = catalog.showAll();
		} catch (TechnicalException e) {
			LOGGER.error("Can not prepare catalog", e);
			return ResourceManager.getInstance().getParametr("error_page");
		}
		
		String role = request.getSession().getAttribute("access").toString();
		switch (ClientType.valueOf(role)) {
		
		case READER:
			page = ResourceManager.getInstance().getParametr("catalog_reader_page");
			break;
			
		case LIBRARIAN:
			page = ResourceManager.getInstance().getParametr("catalog_librarian_page");
			break;
			
		default : 
			LOGGER.warn("Unknown user role. Redirected to authorization");
			page = ResourceManager.getInstance().getParametr("autorization_page");			
		}
		
		request.setAttribute("Books", books);	
		return page;
	}

}
