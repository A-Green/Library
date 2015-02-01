package by.epam.green.library.dao.genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.epam.green.library.dao.AbstractDAO;
import by.epam.green.library.dao.exception.DAOTechnicalException;
import by.epam.green.library.model.BookGenre;

/**
 * The Class MySQLGenreDAO.
 */
public class MySQLGenreDAO extends AbstractDAO implements GenreDAO {

	private Connection connection;

	private static final String GET_GENRE_BY_ID = 
			"SELECT genre.name FROM genre WHERE genre.id = ?";
	
	private static final String GET_ID_BY_NAME = 
			"SELECT genre.id FROM genre WHERE genre.name = ?";
	
	/**
	 * Instantiates a new MySQL genre DAO.
	 *
	 * @param connection the connection
	 */
	public MySQLGenreDAO(Connection connection) {
		this.connection = connection;
	}
	
	/**
	* {@inheritDoc}
	*/
	@Override
	public BookGenre findGenreById(int id) throws DAOTechnicalException {
		BookGenre genre = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			statement = connection.prepareStatement(GET_GENRE_BY_ID);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				genre = BookGenre.valueOf(resultSet.getString(1).toUpperCase());
			}
		} catch (SQLException e) {
			throw new DAOTechnicalException(e);
		} finally {
			closeStatement(statement);
		}

		return genre;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public int findIdByName(BookGenre genre) throws DAOTechnicalException {
		
		String bookGenre = genre.toString().toUpperCase();
		
		int id = 0;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			statement = connection.prepareStatement(GET_ID_BY_NAME);
			statement.setString(1, bookGenre);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				id = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			throw new DAOTechnicalException(e);
		} finally {
			closeStatement(statement);
		}
		
		return id;
	}
}
