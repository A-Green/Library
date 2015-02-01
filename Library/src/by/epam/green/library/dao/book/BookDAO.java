package by.epam.green.library.dao.book;

import by.epam.green.library.dao.exception.DAOLogicException;
import by.epam.green.library.dao.exception.DAOTechnicalException;
import by.epam.green.library.model.Book;

/**
 * The Interface BookDAO.
 */
public interface BookDAO {
	
	 /**
 	 * Adds the book.
 	 *
 	 * @param book the book
 	 * @return id of added book
 	 * @throws DAOTechnicalException the DAO technical exception
 	 */
 	int addBook(Book book) throws DAOTechnicalException;
	 
 	/**
 	 * Removes the book by id.
 	 *
 	 * @param id the book id
 	 * @throws DAOTechnicalException the DAO technical exception
 	 */
 	public void removeBookById(int id) throws DAOTechnicalException;
	 
 	/**
 	 * Adds the book author.
 	 *
 	 * @param authorId the author id
 	 * @param bookId the book id
 	 * @throws DAOTechnicalException the DAO technical exception
 	 */
 	void addBookAuthor(int authorId, int bookId) throws DAOTechnicalException;
	 
 	/**
 	 * Sets the availability of book.
 	 *
 	 * @param bookId the book id
 	 * @param available the availability
 	 * @throws DAOTechnicalException the DAO technical exception
 	 */
 	void setAvailable(int bookId, boolean available) throws DAOTechnicalException;
	 
 	/**
 	 * Grants the book.
 	 *
 	 * @param id the id of book
 	 * @throws DAOTechnicalException the DAO technical exception
 	 * @throws DAOLogicException the DAO logic exception if book is already granted to other reader
 	 */
 	void grantBook(int id) throws DAOTechnicalException, DAOLogicException;

}
