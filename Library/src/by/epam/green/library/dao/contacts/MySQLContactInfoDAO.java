package by.epam.green.library.dao.contacts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import by.epam.green.library.dao.AbstractDAO;
import by.epam.green.library.dao.city.CityDAO;
import by.epam.green.library.dao.city.MySQLCityDAO;
import by.epam.green.library.dao.country.CountryDAO;
import by.epam.green.library.dao.country.MySQLCountryDAO;
import by.epam.green.library.dao.exception.DAOTechnicalException;
import by.epam.green.library.model.ContactInfo;

/**
 * The Class MySQLContactInfoDAO.
 */
public class MySQLContactInfoDAO extends AbstractDAO implements ContactInfoDAO {

	private Connection connection;

	private static final String ADD_CONTACT_INFO = 
			"INSERT INTO contact_info (city_id, country_id, street, phone, email) VALUES(?,?,?,?,?)";
	
	private static final String GET_INFO_BY_ID =
	
	"SELECT street, phone, email, city.name, country.name "
	+ "FROM contact_info JOIN city ON contact_info.city_id = city.id "
	+ "JOIN country ON contact_info.country_id = country.id WHERE contact_info.id = ?";

	/**
	 * Instantiates a new MySQL contact info DAO.
	 *
	 * @param connection the connection
	 */
	public MySQLContactInfoDAO(Connection connection) {
		this.connection = connection;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public int addContactInfo(ContactInfo info) throws DAOTechnicalException {

		int id = 0;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		CountryDAO countryDAO = new MySQLCountryDAO(connection);
		CityDAO cityDAO = new MySQLCityDAO(connection);

		try {

			int cityId = cityDAO.addCity(info.getCity());
			int countryId = countryDAO.addCountry(info.getCountry());

			statement = connection.prepareStatement(ADD_CONTACT_INFO, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, cityId);
			statement.setInt(2, countryId);
			statement.setString(3, info.getStreet());
			statement.setString(4, info.getPhone());
			statement.setString(5, info.getEmail());
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
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

	/**
	* {@inheritDoc}
	*/
	@Override
	public ContactInfo findContactInfoById(int id) throws DAOTechnicalException {

		ContactInfo contactInfo = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {

			statement = connection.prepareStatement(GET_INFO_BY_ID);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				contactInfo = new ContactInfo();
				contactInfo.setStreet(resultSet.getString(1));
				contactInfo.setPhone(resultSet.getString(2));
				contactInfo.setEmail(resultSet.getString(3));
				contactInfo.setCity(resultSet.getString(4));
				contactInfo.setCountry(resultSet.getString(5));
			}
		} catch (SQLException e) {
			throw new DAOTechnicalException(e);
		} finally {
			closeStatement(statement);
		}

		return contactInfo;
	}

}
