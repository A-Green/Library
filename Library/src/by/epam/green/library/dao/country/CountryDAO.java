package by.epam.green.library.dao.country;

import by.epam.green.library.dao.exception.DAOTechnicalException;

/**
 * The Interface CountryDAO.
 */
public interface CountryDAO {
	
	/**
	 * Adds the country.
	 *
	 * @param name the name of country
	 * @return the id of added country
	 * @throws DAOTechnicalException the DAO technical exception
	 */
	int addCountry(String name) throws DAOTechnicalException;
	
	/**
	 * Finds country id by country name.
	 *
	 * @param name the name
	 * @return the id
	 * @throws DAOTechnicalException the DAO technical exception
	 */
	int findIdByName(String name) throws DAOTechnicalException;
}
