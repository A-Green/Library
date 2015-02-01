package by.epam.green.library.commands;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * The Class CommandDispathcer provides method for commands management.
 */
public class CommandDispathcer {
	
	/** The Constant LOGGER. */
	static final Logger LOGGER = Logger.getLogger(CommandDispathcer.class);

	/**
	 * Gets the command by name.
	 *
	 * @param request the request
	 * @return the command
	 * @see by.epam.green.library.commands.Command
	 * @see by.epam.green.library.commands.Commands
	 */
	public Command getCommand(HttpServletRequest request) {

		Command command = null;
		String param = request.getParameter("command").trim().toUpperCase();
		Commands commandType = Commands.valueOf(param);

		switch (commandType) {
		
		case LOGIN:
			command = Commands.LOGIN.getCommand();
			break;

		case LOGOUT:
			command = Commands.LOGOUT.getCommand();
			break;

		case CATALOG:
			command = Commands.CATALOG.getCommand();
			break;

		case ORDERS:
			command = Commands.ORDERS.getCommand();
			break;

		case ADD_BOOK:
			command = Commands.ADD_BOOK.getCommand();
			break;

		case ORDER_BOOK:
			command = Commands.ORDER_BOOK.getCommand();
			break;

		case SEARCH_BOOKS:
			command = Commands.SEARCH_BOOKS.getCommand();
			break;

		case CLIENTS:
			command = Commands.CLIENTS.getCommand();
			break;

		case REGISTRATION:
			command = Commands.REGISTRATION.getCommand();
			break;
			
		case DELETE_BOOKS:
			command = Commands.DELETE_BOOKS.getCommand();
			break;
			
		case GRANT_BOOKS:
			command = Commands.GRANT_BOOKS.getCommand();
			break;
			
		case RETURN_BOOKS:
			command = Commands.RETURN_BOOKS.getCommand();
			break;
			
		case REJECT_ORDER:
			command = Commands.REJECT_ORDER.getCommand();
			break;

		default:
			LOGGER.warn("Unknown operation.");
			command = new EmptyCommand();
		}

		return command;
	}
}
