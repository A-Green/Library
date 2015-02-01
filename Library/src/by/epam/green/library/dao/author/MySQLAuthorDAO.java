package by.epam.green.library.dao.author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import by.epam.green.library.dao.AbstractDAO;
import by.epam.green.library.dao.exception.DAOTechnicalException;
import by.epam.green.library.model.Author;

/**
 * The Class MySQLAuthorDAO.
 */
public class MySQLAuthorDAO extends AbstractDAO implements AuthorDAO {

	private Connection connection;

	private static final String GET_AUTHOR_BY_ID = "SELECT * FROM author WHERE author.id = ?";
	
	private static final String GET_AUTHORS_BY_BOOK_ID = 
			"SELECT id, name FROM book_author "
			+ "INNER JOIN author ON book_author.author_id = author.id WHERE book_id = ?";
	
	private static final String GET_AUTHOR_BY_NAME = "SELECT * FROM author WHERE name = ?";

	private static final String ADD_AUTHOR = 
			"INSERT INTO author (name) VALUES( ? )";

	/**
	 * Instantiates a new MySQL author DAO.
	 *
	 * @param connection the connection
	 */
	public MySQLAuthorDAO(Connection connection) {
		this.connection = connection;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public Author findAuthorById(int id) throws DAOTechnicalException {

		Author author = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			statement = connection.prepareStatement(GET_AUTHOR_BY_ID);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				author = new Author();
				author.setId(resultSet.getInt(1));
				author.setName(resultSet.getString(2));
			}
		} catch (SQLException e) {
			throw new DAOTechnicalException(e);
		} finally {
			closeStatement(statement);
		}
		return author;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public ArrayList<Author> findAuthorsByBookId(int id)
			throws DAOTechnicalException {
		
		ArrayList<Author> authors = new ArrayList<Author>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			statement = connection.prepareStatement(GET_AUTHORS_BY_BOOK_ID);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Author author = new Author();
				author.setId(resultSet.getInt(1));
				author.setName(resultSet.getString(2));
				authors.add(author);
			}
		} catch (SQLException e) {
			throw new DAOTechnicalException(e);
		} finally {
			closeStatement(statement);
		}

		return authors;
	}
	
	/**
	* {@inheritDoc}
	*/
	@Override
	public Author findAuthorByName(String name) throws DAOTechnicalException {
		
		Author author = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			statement = connection.prepareStatement(GET_AUTHOR_BY_NAME);
			statement.setString(1, name);
			resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				author = new Author();
				author.setId(resultSet.getInt(1));
				author.setName(name);
			}
		} catch (SQLException e) {
			throw new DAOTechnicalException(e);
		} finally {
			closeStatement(statement);
		}
		
		return author;
	}
	
	/**
	* {@inheritDoc}
	*/
	@Override
	public int addAuthor(Author author) throws DAOTechnicalException {

		int authorId = 0;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			Author existedAuthor = findAuthorByName(author.getName());
			
			if (existedAuthor != null) {
				return existedAuthor.getId();
			} 

			statement = connection.prepareStatement(ADD_AUTHOR, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, author.getName());
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			
			if (resultSet.next()) {
				authorId = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			throw new DAOTechnicalException(e);
		} finally {
			closeStatement(statement);
		}
		
		return authorId;
	}
}
