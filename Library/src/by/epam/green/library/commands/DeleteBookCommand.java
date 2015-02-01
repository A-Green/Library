package by.epam.green.library.commands;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.green.library.logic.CatalogOptions;
import by.epam.green.library.logic.exception.TechnicalException;
import by.epam.green.library.managers.ResourceManager;
import by.epam.green.library.model.Book;

/**
 * The Class DeleteBookCommand.
 */
public class DeleteBookCommand implements Command {

	/** The Constant LOGGER. */
	static final Logger LOGGER = Logger.getLogger(DeleteBookCommand.class);

	/**
	* {@inheritDoc}
	* 
	*/
	@Override
	public String execute(HttpServletRequest request) {

		//ids of selected to delete books
		String[] selected = request.getParameterValues("selected");

		if (selected == null || selected.length == 0) {
			return ResourceManager.getInstance().getParametr("catalog_librarian_page");
		}

		CatalogOptions options = new CatalogOptions();

		for (int i = 0; i < selected.length; i++) {

			int bookId = Integer.parseInt(selected[i]);
			
			try {
				options.deleteBook(bookId);
			} catch (TechnicalException e) {
				LOGGER.error("Can not delete book", e);
				return ResourceManager.getInstance().getParametr("error_page");
			}	
		}
		
		ArrayList<Book> books = new ArrayList<Book>();	
		
		try {
			books = options.showAll();
		} catch (TechnicalException e) {
			LOGGER.error("Can not prepare catalog", e);
			return ResourceManager.getInstance().getParametr("error_page");
		}
		
		request.setAttribute("Books", books);	

		return ResourceManager.getInstance().getParametr("catalog_librarian_page");
	}

}
