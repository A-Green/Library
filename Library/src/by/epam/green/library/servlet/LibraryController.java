package by.epam.green.library.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.green.library.commands.Command;
import by.epam.green.library.commands.CommandDispathcer;

/**
 * The Class LibraryController.Http Servlet, that controls and manages users requests
 */
@WebServlet("/LibraryController")
public class LibraryController extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	* {@inheritDoc}
	*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		processRequest(request, response);

	}

	/**
	* {@inheritDoc}
	*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		processRequest(request, response);

	}

	/**
	 * Process request.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String page = null;
		
		CommandDispathcer commandDispathcer = new CommandDispathcer();
		
		Command command = commandDispathcer.getCommand(request);
		
		page = command.execute(request);
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		
		dispatcher.forward(request, response);	

	}
}
