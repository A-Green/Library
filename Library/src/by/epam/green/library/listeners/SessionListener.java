package by.epam.green.library.listeners;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * The listener interface for receiving session events.
 * The class that is interested in processing a session
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addSessionListener<code> method. When
 * the session event occurs, that object's appropriate
 * method is invoked.
 *
 * @see SessionEvent
 */
public class SessionListener implements HttpSessionListener {

	/**
	* {@inheritDoc}
	*/
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		//sets default locale
		event.getSession().setAttribute("locale", "ru_RU");
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		
	}

}
