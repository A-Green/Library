package by.epam.green.library.commands;

import javax.servlet.http.HttpServletRequest;

import by.epam.green.library.managers.ResourceManager;

/**
 * The Class EmptyCommand.
 */
public class EmptyCommand implements Command {

	/**
	* {@inheritDoc}
	* 
	* @return authorization page
	*/
	@Override
	public String execute(HttpServletRequest request) {
		return ResourceManager.getInstance().getParametr("login_faild_page");
	}

}
