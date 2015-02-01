package by.epam.green.library.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import by.epam.green.library.connectpool.ConnectionPool;
import by.epam.green.library.dao.factory.DAOFactoryCreator;

/**
 * The listener interface for receiving context events.
 * The class that is interested in processing a context
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addContextListener<code> method. When
 * the context event occurs, that object's appropriate
 * method is invoked.
 *
 * @see ContextEvent
 */
public class ContextListener implements ServletContextListener{

	/**
	* {@inheritDoc}
	* 
	*/
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		//returns all connections to connection pool
		ConnectionPool.getInstance().returnAllConnections();
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		//inits DAOFactoryCreator
		String database = arg0.getServletContext().getInitParameter("database");
		DAOFactoryCreator.initFactory(database);
	}
}
