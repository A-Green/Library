package by.epam.green.library.dao.city;

import by.epam.green.library.dao.exception.DAOTechnicalException;

/**
 * The Interface CityDAO.
 */
public interface CityDAO {

	/**
	 * Adds the city.
	 *
	 * @param name the city name
	 * @return id of added city
	 * @throws DAOTechnicalException the DAO technical exception
	 */
	int addCity(String name) throws DAOTechnicalException;
	
	/**
	 * Find id by name.
	 *
	 * @param name the city name
	 * @return the id of founded city or null if there is no city with such name
	 * @throws DAOTechnicalException the DAO technical exception
	 */
	int findIdByName(String name) throws DAOTechnicalException;
}
