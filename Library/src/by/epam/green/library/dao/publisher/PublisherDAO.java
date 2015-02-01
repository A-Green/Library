package by.epam.green.library.dao.publisher;

import by.epam.green.library.dao.exception.DAOTechnicalException;
import by.epam.green.library.model.Publisher;

/**
 * The Interface PublisherDAO.
 */
public interface PublisherDAO {

	/**
	 * Find publisher by id.
	 *
	 * @param id the id
	 * @return the publisher
	 * @throws DAOTechnicalException the DAO technical exception
	 */
	Publisher findPublisherById(int id) throws DAOTechnicalException;
	
	/**
	 * Adds the publisher.
	 *
	 * @param publisher new publisher
	 * @return the id of added publisher
	 * @throws DAOTechnicalException the DAO technical exception
	 */
	int addPublisher(Publisher publisher) throws DAOTechnicalException;
	
	/**
	 * Find publisher by name.
	 *
	 * @param name the name
	 * @return the publisher
	 * @throws DAOTechnicalException the DAO technical exception
	 */
	Publisher findPublisherByName(String name) throws DAOTechnicalException;

}
