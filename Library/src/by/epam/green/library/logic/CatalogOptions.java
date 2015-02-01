package by.epam.green.library.logic;

import java.sql.Connection;
import java.util.ArrayList;

import by.epam.green.library.connectpool.ConnectionPool;
import by.epam.green.library.dao.book.BookDAO;
import by.epam.green.library.dao.exception.DAOTechnicalException;
import by.epam.green.library.dao.factory.DAOFactoryCreator;
import by.epam.green.library.dao.search.BookSearchDAO;
import by.epam.green.library.logic.exception.LogicException;
import by.epam.green.library.logic.exception.TechnicalException;
import by.epam.green.library.model.Author;
import by.epam.green.library.model.Book;
import by.epam.green.library.model.BookGenre;
import by.epam.green.library.model.Publisher;

/**
 * The Class CatalogOptions provides catalog operations logic
 */
public class CatalogOptions {

	/**
	 * Show all books.
	 * 
	 * @return the array list of books
	 * @throws TechnicalException the technical exception
	 */
	public ArrayList<Book> showAll() throws TechnicalException {

		Connection connection = ConnectionPool.getInstance().getConnection();
		BookSearchDAO searchDAO = DAOFactoryCreator.getFactory().createBookSearchDAO(connection);
		ArrayList<Book> books = new ArrayList<Book>();

		try {
			books = searchDAO.findAllBooks();
		} catch (DAOTechnicalException e) {
			throw new TechnicalException(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}

		return books;
	}

	/**
	 * Adds the book.
	 * 
	 * @param title the title
	 * @param authors the authors
	 * @param genrethe genre
	 * @param publisher the publisher
	 * @param year the year
	 * @throws TechnicalException the technical exception
	 * @throws LogicException the logic exception params are invalids
	 */
	public void addBook(String title, String authors, String genre, String publisher, String year)
			throws TechnicalException, LogicException {

		if (title == null) {
			throw new LogicException("Invalid field value");
		}

		if (authors.isEmpty() || genre.isEmpty() || publisher.isEmpty()
				|| year.isEmpty() || title.isEmpty()) {

			throw new LogicException("Invalid field value");
		}

		Publisher bookPublisher = new Publisher();
		bookPublisher.setName(publisher);

		String[] tempAuthors = authors.split(",");
		ArrayList<Author> bookAuthors = new ArrayList<Author>();

		for (int i = 0; i < tempAuthors.length; i++) {
			Author author = new Author();
			author.setName(tempAuthors[i].trim());
			bookAuthors.add(author);
		}

		Book book = new Book();
		book.setTitle(title);
		book.setAuthors(bookAuthors);
		book.setGenre(BookGenre.valueOf(genre));
		book.setYear(Integer.parseInt(year));
		book.setPublisher(bookPublisher);

		Connection connection = ConnectionPool.getInstance().getConnection();
		BookDAO bookDAO = DAOFactoryCreator.getFactory().createBookDAO(connection);

		try {
			bookDAO.addBook(book);
		} catch (DAOTechnicalException e) {
			throw new TechnicalException(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}

	}

	/**
	 * Delete book.
	 * 
	 * @param bookId the book id
	 * @throws TechnicalException the technical exception
	 */
	public void deleteBook(int bookId) throws TechnicalException {

		Connection connection = ConnectionPool.getInstance().getConnection();
		BookDAO bookDAO = DAOFactoryCreator.getFactory().createBookDAO(connection);

		try {
			bookDAO.removeBookById(bookId);
		} catch (DAOTechnicalException e) {
			throw new TechnicalException(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}
	}
}
