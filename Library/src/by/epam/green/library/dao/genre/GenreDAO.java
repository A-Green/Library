package by.epam.green.library.dao.genre;

import by.epam.green.library.dao.exception.DAOTechnicalException;
import by.epam.green.library.model.BookGenre;

/**
 * The Interface GenreDAO.
 */
public interface GenreDAO {

	/**
	 * Find genre by id.
	 *
	 * @param id the id
	 * @return the book genre
	 * @throws DAOTechnicalException the DAO technical exception
	 */
	BookGenre findGenreById(int id) throws DAOTechnicalException;
	
	/**
	 * Find genre id by name.
	 *
	 * @param genre the genre
	 * @return the id of genre
	 * @throws DAOTechnicalException the DAO technical exception
	 */
	int findIdByName(BookGenre genre) throws DAOTechnicalException;
}
