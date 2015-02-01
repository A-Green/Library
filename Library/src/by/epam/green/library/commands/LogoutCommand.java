package by.epam.green.library.commands;

import javax.servlet.http.HttpServletRequest;

import by.epam.green.library.managers.ResourceManager;

/**
 * The Class LogoutCommand.
 */
public class LogoutCommand implements Command {

	/**
	* {@inheritDoc}
	*/
	@Override
	public String execute(HttpServletRequest request) {
		
		request.getSession().invalidate();
		return ResourceManager.getInstance().getParametr("autorization_page");
	}

}
