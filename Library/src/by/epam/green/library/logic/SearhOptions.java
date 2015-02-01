package by.epam.green.library.logic;

import java.sql.Connection;
import java.util.ArrayList;

import by.epam.green.library.connectpool.ConnectionPool;
import by.epam.green.library.dao.exception.DAOTechnicalException;
import by.epam.green.library.dao.factory.DAOFactoryCreator;
import by.epam.green.library.dao.search.BookSearchDAO;
import by.epam.green.library.logic.exception.TechnicalException;
import by.epam.green.library.model.Author;
import by.epam.green.library.model.Book;
import by.epam.green.library.model.BookGenre;

/**
 * The Class SearhOptions provides book search methods
 */
public class SearhOptions {

	/**
	 * Extended search. Search by title, genre and author
	 *
	 * @param title the title
	 * @param genre the genre
	 * @param author the author
	 * @return the array list of books
	 * @throws TechnicalException the technical exception
	 */
	public ArrayList<Book> extendedSearch(String title, String genre, String author) throws TechnicalException {

		ArrayList<Book> foundBooks = new ArrayList<Book>();
		ArrayList<Book> result = new ArrayList<Book>();

		foundBooks = searchByFirstNotEmpty(title, genre, author);

		for (Book book : foundBooks) {

			if (!title.isEmpty()) {
				if (!book.getTitle().toLowerCase().contains(title.toLowerCase())) {
					continue;
				}
			}

			if (!genre.isEmpty()) {
				if (!book.getGenre().toString().equals(genre)) {
					continue;
				}
			}

			if (!author.isEmpty()) {
				int authorMatch = 0;

				for (Author bookAuthor : book.getAuthors()) {
					if (bookAuthor.getName().toLowerCase().contains(author.toLowerCase())) {
						authorMatch++;
					}
				}

				if (authorMatch == 0) {
					continue;
				}
			}

			result.add(book);
		}

		return result;
	}

	/**
	 * Search by first not empty. 
	 * Finds books by first parameter that not null and not empty
	 *
	 * @param title the title
	 * @param genre the genre
	 * @param author the author
	 * @return the array list of books
	 * @throws TechnicalException the technical exception
	 */
	private ArrayList<Book> searchByFirstNotEmpty(String title, String genre, String author) 
			throws TechnicalException {

		Connection connection = ConnectionPool.getInstance().getConnection();
		BookSearchDAO searchDAO = DAOFactoryCreator.getFactory().createBookSearchDAO(connection);

		ArrayList<Book> foundBooks = new ArrayList<Book>();

		if (title != null && !title.isEmpty()) {

			try {
				foundBooks = searchDAO.findBooksWithTitleLike(title);
			} catch (DAOTechnicalException e) {
				throw new TechnicalException(e);
			}

		} else if (genre != null && !genre.isEmpty()) {

			try {
				foundBooks = searchDAO.findBooksByGenre(BookGenre.valueOf(genre));
			} catch (DAOTechnicalException e) {
				throw new TechnicalException(e);
			}

		} else if (author != null && !author.isEmpty()) {

			try {
				foundBooks = searchDAO.findBooksByAuthor(author);
			} catch (DAOTechnicalException e) {
				throw new TechnicalException(e);
			}
		} else {

			try {
				foundBooks = searchDAO.findAllBooks();
			} catch (DAOTechnicalException e) {
				throw new TechnicalException(e);
			}
		}
		return foundBooks;
	}
}
