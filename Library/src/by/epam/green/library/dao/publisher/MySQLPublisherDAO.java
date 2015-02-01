package by.epam.green.library.dao.publisher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import by.epam.green.library.dao.AbstractDAO;
import by.epam.green.library.dao.exception.DAOTechnicalException;
import by.epam.green.library.model.Publisher;

/**
 * The Class MySQLPublisherDAO.
 */
public class MySQLPublisherDAO extends AbstractDAO implements PublisherDAO {

	private Connection connection;

	private static final String GET_PUBLISHER_BY_ID = 
			" SELECT * FROM publisher WHERE publisher.id = ?";
	
	private static final String GET_PUBLISHER_BY_NAME = 
			"SELECT id FROM publisher WHERE name = ?";
	
	private static final String ADD_PUBLISHER = 
			"INSERT INTO publisher (name) VALUES( ? )";

	/**
	 * Instantiates a new MySQL publisher DAO.
	 *
	 * @param connection the connection
	 */
	public MySQLPublisherDAO(Connection connection) {
		this.connection = connection;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public Publisher findPublisherById(int id) throws DAOTechnicalException {
		
		Publisher publisher = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			statement = connection.prepareStatement(GET_PUBLISHER_BY_ID);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
	
			if (resultSet.next()) {
				publisher = new Publisher();
				publisher.setId(resultSet.getInt(1));
				publisher.setName(resultSet.getString(2));
			}
		} catch (SQLException e) {
			throw new DAOTechnicalException(e);
		} finally {
			closeStatement(statement);
		}	
		return publisher;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public int addPublisher(Publisher publisher) throws DAOTechnicalException {
		
		Publisher existedPublisher = null;
		int publisherId = 0;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			existedPublisher = findPublisherByName(publisher.getName());
			
			if (existedPublisher != null) {
				return existedPublisher.getId();
			} 

			statement = connection.prepareStatement(ADD_PUBLISHER, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, publisher.getName());
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				publisherId = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			throw new DAOTechnicalException(e);
		} finally {
			closeStatement(statement);
		}		
		return publisherId;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public Publisher findPublisherByName(String name)
			throws DAOTechnicalException {
		
		Publisher publisher = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			statement = connection.prepareStatement(GET_PUBLISHER_BY_NAME);
			statement.setString(1, name);
			resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				publisher = new Publisher();
				publisher.setId(resultSet.getInt(1));
				publisher.setName(name);
			}
		} catch (SQLException e) {
			throw new DAOTechnicalException(e);
		} finally {
			closeStatement(statement);
		}
		
		return publisher;
	}
}
