package by.epam.green.library.commands;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import by.epam.green.library.logic.ClientsOptions;
import by.epam.green.library.logic.exception.TechnicalException;
import by.epam.green.library.managers.ResourceManager;
import by.epam.green.library.model.Client;
import by.epam.green.library.model.OrderStatus;

/**
 * The Class ShowClientsCommand.
 */
public class ShowClientsCommand implements Command {
	
	/** The Constant LOGGER. */
	static final Logger LOGGER = Logger.getLogger(ShowClientsCommand.class);

	/**
	* {@inheritDoc}
	*/
	@Override
	public String execute(HttpServletRequest request) {

		ClientsOptions options = new ClientsOptions();
		ArrayList<Client> clients = new ArrayList<Client>();
		String clientName = request.getParameter("clientName");

		if (clientName == null) {		
			try {
				clients = options.showClientsByOrderStatus(OrderStatus.REQUESTED);
			} catch (TechnicalException e) {
				LOGGER.error(e);
				return ResourceManager.getInstance().getParametr("error_page");
			}

		} else {

			try {
				clients = options.showClientsByName(clientName);
			} catch (TechnicalException e) {
				LOGGER.error(e);
				return ResourceManager.getInstance().getParametr("error_page");
			}
		}

		request.setAttribute("Clients", clients);
		return ResourceManager.getInstance().getParametr("clients");
	}

}
