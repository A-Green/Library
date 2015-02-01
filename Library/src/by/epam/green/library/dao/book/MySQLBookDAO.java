package by.epam.green.library.dao.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import by.epam.green.library.dao.AbstractDAO;
import by.epam.green.library.dao.author.AuthorDAO;
import by.epam.green.library.dao.author.MySQLAuthorDAO;
import by.epam.green.library.dao.exception.DAOLogicException;
import by.epam.green.library.dao.exception.DAOTechnicalException;
import by.epam.green.library.dao.genre.GenreDAO;
import by.epam.green.library.dao.genre.MySQLGenreDAO;
import by.epam.green.library.dao.publisher.MySQLPublisherDAO;
import by.epam.green.library.dao.publisher.PublisherDAO;
import by.epam.green.library.model.Author;
import by.epam.green.library.model.Book;

/**
 * The Class MySQLBookDAO.
 */
public class MySQLBookDAO extends AbstractDAO implements BookDAO {
	
	private Connection connection;
	
	private static final String ADD_BOOK = 
			"INSERT INTO book (title, publisher_id, year, genre_id, available)"
			+ "VALUES(?, ?, ?, ?, ?)";
	
	private static final String ADD_BOOK_AUTHOR = 
			"INSERT INTO book_author VALUES(?, ?)";
	
	private static final String SET_AVAILABLE = "UPDATE book SET available = ? WHERE id = ?";
	
	private static final String GRANT = "UPDATE book SET available = false WHERE id = ? AND available = true";
	
	private static final String DELETE_BY_ID = "DELETE FROM book WHERE id = ?";
	
	/**
	 * Instantiates a new MySQL book DAO.
	 *
	 * @param connection the connection
	 */
	public MySQLBookDAO(Connection connection) {
		this.connection = connection;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public int addBook(Book book) throws DAOTechnicalException {
		
		int bookId = 0;
		PublisherDAO publisherDAO = new MySQLPublisherDAO(connection);
		GenreDAO genreDAO = new MySQLGenreDAO(connection);
		AuthorDAO authorDAO = new MySQLAuthorDAO(connection);
		
		int publisherId = publisherDAO.addPublisher(book.getPublisher());
		int genreId = genreDAO.findIdByName(book.getGenre());
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(ADD_BOOK, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1,book.getTitle());
			statement.setInt(2, publisherId);
			statement.setInt(3, book.getYear());
			statement.setInt(4, genreId);
			statement.setBoolean(5, true);
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				bookId = resultSet.getInt(1);
			}
			
			for (Author author: book.getAuthors()) {
				int authorId = authorDAO.addAuthor(author);
				addBookAuthor(authorId, bookId);
			}

		} catch (SQLException e) {
			throw new DAOTechnicalException(e);
		} finally {
			closeStatement(statement);
		}
		
		return bookId;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public void addBookAuthor(int authorId, int bookId) throws DAOTechnicalException {
		
		PreparedStatement statement = null;
		
		try {
			statement = connection.prepareStatement(ADD_BOOK_AUTHOR);
			statement.setInt(1, authorId);
			statement.setInt(2, bookId);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOTechnicalException(e);
		} finally {
			closeStatement(statement);
		}		
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public void setAvailable(int bookId, boolean available) throws DAOTechnicalException {

		PreparedStatement statement = null;
		
		try {
			statement = connection.prepareStatement(SET_AVAILABLE);
			statement.setBoolean(1, available);
			statement.setInt(2, bookId);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOTechnicalException(e);
		} finally {
			closeStatement(statement);
		}		
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public void grantBook(int id) throws DAOTechnicalException, DAOLogicException {
		
		PreparedStatement statement = null;
		
		try {
			statement = connection.prepareStatement(GRANT);
			statement.setInt(1, id);
			int count = statement.executeUpdate();
			if (count != 1) {
				throw new DAOLogicException("book is not available");
			}
			
		} catch (SQLException e) {
			throw new DAOTechnicalException(e);
		} finally {
			closeStatement(statement);
		}		
		
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public void removeBookById(int id) throws DAOTechnicalException {
		
		PreparedStatement statement = null;
		
		try {
			statement = connection.prepareStatement(DELETE_BY_ID);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOTechnicalException(e);
		} finally {
			closeStatement(statement);
		}		
	}
}
