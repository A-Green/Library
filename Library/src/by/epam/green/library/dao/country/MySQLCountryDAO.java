package by.epam.green.library.dao.country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import by.epam.green.library.dao.AbstractDAO;
import by.epam.green.library.dao.exception.DAOTechnicalException;

/**
 * The Class MySQLCountryDAO.
 */
public class MySQLCountryDAO extends AbstractDAO implements CountryDAO {
	
	private Connection connection;

	private static final String ADD_COUNTRY = "INSERT INTO country (name) VALUES(?)";

	private static final String GET_COUNTRY_ID_BY_NAME = "SELECT id FROM country WHERE name = ?";

	/**
	 * Instantiates a new MySQL country DAO.
	 *
	 * @param connection the connection
	 */
	public MySQLCountryDAO(Connection connection) {
		this.connection = connection;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public int addCountry(String name) throws DAOTechnicalException {

		int countryId = 0;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			countryId = findIdByName(name);

			if (countryId != 0) {
				return countryId;
			}

			statement = connection.prepareStatement(ADD_COUNTRY, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, name);
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();

			if (resultSet.next()) {
				countryId = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOTechnicalException(e);
		} finally {
			closeStatement(statement);
		}

		return countryId;
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
			statement = connection.prepareStatement(GET_COUNTRY_ID_BY_NAME);
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
