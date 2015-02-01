package by.epam.green.library.dao.search;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import by.epam.green.library.dao.AbstractDAO;
import by.epam.green.library.dao.author.AuthorDAO;
import by.epam.green.library.dao.author.MySQLAuthorDAO;
import by.epam.green.library.dao.exception.DAOTechnicalException;
import by.epam.green.library.dao.genre.GenreDAO;
import by.epam.green.library.dao.genre.MySQLGenreDAO;
import by.epam.green.library.dao.publisher.MySQLPublisherDAO;
import by.epam.green.library.dao.publisher.PublisherDAO;
import by.epam.green.library.model.Book;
import by.epam.green.library.model.BookGenre;

/**
 * The Class MySQLBookSearchDAO.
 */
public class MySQLBookSearchDAO extends AbstractDAO implements BookSearchDAO {

	/** The connection. */
	private Connection connection;

	private static final String GET_BOOKS_WITH_TITLE_LIKE = "SELECT * FROM book WHERE title LIKE ?";
	
	private static final String GET_BOOKS_BY_GENRE = 
			"select * FROM book JOIN genre ON book.genre_id = genre.id WHERE genre.name = ?";
	
	private static final String GET_BOOKS_BY_AUTHOR = 
			"SELECT book.id, title, publisher_id, year, genre_id, available "
			+ "FROM (book JOIN book_author ON book.id = book_author.book_id) "
			+ "JOIN author ON author_id = author.id WHERE author.name LIKE ?";
	
	private static final String GET_ALL_BOOKS = "SELECT * FROM book";
	
	/**
	 * Instantiates a new mysql booksearch dao.
	 *
	 * @param connection the connection
	 */
	public MySQLBookSearchDAO(Connection connection) {
		this.connection = connection;
	}

	/* (non-Javadoc)
	 * @see by.epam.green.library.dao.search.BookSearchDAO#findBooksWhithTitleLike(java.lang.String)
	 */
	@Override
	public ArrayList<Book> findBooksWithTitleLike(String title)
			throws DAOTechnicalException {
		
		ArrayList<Book> result = new ArrayList<Book>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		AuthorDAO authorDAO = new MySQLAuthorDAO(connection);	
		PublisherDAO publisherDAO = new MySQLPublisherDAO(connection);
		GenreDAO genreDAO = new MySQLGenreDAO(connection);
		
		title = "%" + title + "%";
		
		try {
			statement = connection.prepareStatement(GET_BOOKS_WITH_TITLE_LIKE);
			statement.setString(1, title);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Book book = new Book();
				int bookId = resultSet.getInt(1);
				book.setId(bookId);
				book.setTitle(resultSet.getString(2));
				book.setAuthors(authorDAO.findAuthorsByBookId(bookId));
				book.setPublisher(publisherDAO.findPublisherById(resultSet.getInt(3)));
				book.setYear(resultSet.getInt(4));
				book.setGenre(genreDAO.findGenreById(resultSet.getInt(5)));
				book.setAvailable(resultSet.getBoolean(6));
				result.add(book);
			}
		} catch (SQLException e) {
			throw new DAOTechnicalException(e);
		} finally {
			closeStatement(statement);
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see by.epam.green.library.dao.search.BookSearchDAO#findBooksByGenre(by.epam.green.library.model.BookGenre)
	 */
	@Override
	public ArrayList<Book> findBooksByGenre(BookGenre genre) throws DAOTechnicalException {
		
		ArrayList<Book> result = new ArrayList<Book>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		AuthorDAO authorDAO = new MySQLAuthorDAO(connection);	
		PublisherDAO publisherDAO = new MySQLPublisherDAO(connection);
		
		try {
			statement = connection.prepareStatement(GET_BOOKS_BY_GENRE);
			statement.setString(1, genre.toString());
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Book book = new Book();
				int bookId = resultSet.getInt(1);
				book.setId(bookId);
				book.setTitle(resultSet.getString(2));
				book.setAuthors(authorDAO.findAuthorsByBookId(bookId));
				book.setPublisher(publisherDAO.findPublisherById(resultSet.getInt(3)));
				book.setYear(resultSet.getInt(4));
				book.setGenre(genre);
				book.setAvailable(resultSet.getBoolean(6));
				result.add(book);
			}
		} catch (SQLException e) {
			throw new DAOTechnicalException(e);
		} finally {
			closeStatement(statement);
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see by.epam.green.library.dao.search.BookSearchDAO#findBooksByAuthor(java.lang.String)
	 */
	@Override
	public ArrayList<Book> findBooksByAuthor(String name) throws DAOTechnicalException {
		
		ArrayList<Book> result = new ArrayList<Book>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		AuthorDAO authorDAO = new MySQLAuthorDAO(connection);	
		PublisherDAO publisherDAO = new MySQLPublisherDAO(connection);
		GenreDAO genreDAO = new MySQLGenreDAO(connection);
		
		try {
			statement = connection.prepareStatement(GET_BOOKS_BY_AUTHOR);
			statement.setString(1,"%" + name + "%");
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Book book = new Book();
				int bookId = resultSet.getInt(1);
				book.setId(bookId);
				book.setTitle(resultSet.getString(2));
				book.setAuthors(authorDAO.findAuthorsByBookId(bookId));
				book.setPublisher(publisherDAO.findPublisherById(resultSet.getInt(3)));
				book.setYear(resultSet.getInt(4));
				book.setGenre(genreDAO.findGenreById(resultSet.getInt(5)));
				book.setAvailable(resultSet.getBoolean(6));
				result.add(book);
			}
		} catch (SQLException e) {
			throw new DAOTechnicalException(e);
		} finally {
			closeStatement(statement);
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see by.epam.green.library.dao.search.BookSearchDAO#findAllBooks()
	 */
	@Override
	public ArrayList<Book> findAllBooks() throws DAOTechnicalException {

			ArrayList<Book> result = new ArrayList<Book>();
			PreparedStatement statement = null;
			ResultSet resultSet = null;
			
			AuthorDAO authorDAO = new MySQLAuthorDAO(connection);	
			PublisherDAO publisherDAO = new MySQLPublisherDAO(connection);
			GenreDAO genreDAO = new MySQLGenreDAO(connection);
			
			try {
				statement = connection.prepareStatement(GET_ALL_BOOKS);
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					
					Book book = new Book();
					int bookId = resultSet.getInt(1);
					book.setId(bookId);
					book.setTitle(resultSet.getString(2));
					book.setAuthors(authorDAO.findAuthorsByBookId(bookId));
					book.setPublisher(publisherDAO.findPublisherById(resultSet.getInt(3)));
					book.setYear(resultSet.getInt(4));
					book.setGenre(genreDAO.findGenreById(resultSet.getInt(5)));
					book.setAvailable(resultSet.getBoolean(6));
					result.add(book);
				}
			} catch (SQLException e) {
				throw new DAOTechnicalException(e);
			} finally {
				closeStatement(statement);
			}
			return result;
		}
}
