package by.epam.green.library.dao.contacts;

import by.epam.green.library.dao.exception.DAOTechnicalException;
import by.epam.green.library.model.ContactInfo;

/**
 * The Interface ContactInfoDAO.
 */
public interface ContactInfoDAO {

	/**
	 * Adds client's contact info.
	 *
	 * @param info the info
	 * @return the id of added info
	 * @throws DAOTechnicalException the DAO technical exception
	 */
	int addContactInfo(ContactInfo info) throws DAOTechnicalException;
	
	/**
	 * Find contact info by id.
	 *
	 * @param id the id
	 * @return the client contact info
	 * @throws DAOTechnicalException the DAO technical exception
	 */
	ContactInfo findContactInfoById(int id) throws DAOTechnicalException;
}
