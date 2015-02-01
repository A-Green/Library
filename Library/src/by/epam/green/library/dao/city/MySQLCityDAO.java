package by.epam.green.library.dao.city;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import by.epam.green.library.dao.AbstractDAO;
import by.epam.green.library.dao.exception.DAOTechnicalException;

/**
 * The Class MySQLCityDAO.
 */
public class MySQLCityDAO extends AbstractDAO implements CityDAO {

	private Connection connection;

	private static final String ADD_CITY = "INSERT INTO city (name) VALUES(?)";

	private static final String GET_SITY_ID_BY_NAME = "SELECT id FROM city WHERE name = ?";

	/**
	 * Instantiates a new MySQL city DAO.
	 *
	 * @param connection the connection
	 */
	public MySQLCityDAO(Connection connection) {
		this.connection = connection;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public int addCity(String name) throws DAOTechnicalException {

		int cityId = 0;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			cityId = findIdByName(name);

			if (cityId != 0) {
				return cityId;
			}

			statement = connection.prepareStatement(ADD_CITY, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, name);
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();

			if (resultSet.next()) {
				cityId = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			throw new DAOTechnicalException(e);
		} finally {
			closeStatement(statement);
		}

		return cityId;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public int findIdByName(String name) throws DAOTechnicalException {

		int id = 0;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			statement = connection.prepareStatement(GET_SITY_ID_BY_NAME);
			statement.setString(1, name);
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
