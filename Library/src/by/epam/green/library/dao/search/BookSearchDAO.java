package by.epam.green.library.dao.search;

import java.util.ArrayList;

import by.epam.green.library.dao.exception.DAOTechnicalException;
import by.epam.green.library.model.Book;
import by.epam.green.library.model.BookGenre;

/**
 * The Interface BookSearchDAO.
 */
public interface BookSearchDAO {

	/**
	 * Find books with title contains.
	 *
	 * @param title the title
	 * @return the array list of books
	 * @throws DAOTechnicalException the DAO technical exception
	 */
	ArrayList<Book> findBooksWithTitleLike(String title) throws DAOTechnicalException;
	
	/**
	 * Find books by genre.
	 *
	 * @param genre the genre
	 * @return the array list of books
	 * @throws DAOTechnicalException the DAO technical exception
	 */
	ArrayList<Book> findBooksByGenre(BookGenre genre) throws DAOTechnicalException;
	
	/**
	 * Find books by author.
	 *
	 * @param name the author name
	 * @return the array list of books
	 * @throws DAOTechnicalException the DAO technical exception
	 */
	ArrayList<Book> findBooksByAuthor(String name) throws DAOTechnicalException;
	
	/**
	 * Find all books.
	 *
	 * @return the array list of all books
	 * @throws DAOTechnicalException the DAO technical exception
	 */
	ArrayList<Book> findAllBooks() throws DAOTechnicalException; 
}
