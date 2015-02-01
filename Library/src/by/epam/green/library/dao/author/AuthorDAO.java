package by.epam.green.library.dao.author;

import java.util.ArrayList;

import by.epam.green.library.dao.exception.DAOTechnicalException;
import by.epam.green.library.model.Author;

/**
 * The Interface AuthorDAO.
 */
public interface AuthorDAO {

	/**
	 * Finds author by id.
	 *
	 * @param id the author id
	 * @return the author
	 * @throws DAOTechnicalException the DAO technical exception
	 */
	Author findAuthorById(int id) throws DAOTechnicalException;
	
	/**
	 * Find author by name.
	 *
	 * @param name the author name
	 * @return the author
	 * @throws DAOTechnicalException the DAO technical exception
	 */
	Author findAuthorByName(String name) throws DAOTechnicalException;
	
	/**
	 * Adds the author.
	 *
	 * @param author the author
	 * @return id of added author
	 * @throws DAOTechnicalException the DAO technical exception
	 */
	int addAuthor(Author author) throws DAOTechnicalException;
	
	/**
	 * Find authors by book id.
	 *
	 * @param id the id
	 * @return the array list of book authors
	 * @throws DAOTechnicalException the DAO technical exception
	 */
	ArrayList<Author> findAuthorsByBookId(int id) throws DAOTechnicalException;
}
