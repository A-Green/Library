package by.epam.green.library.dao.factory;

import java.sql.Connection;

import by.epam.green.library.dao.author.AuthorDAO;
import by.epam.green.library.dao.author.MySQLAuthorDAO;
import by.epam.green.library.dao.book.BookDAO;
import by.epam.green.library.dao.book.MySQLBookDAO;
import by.epam.green.library.dao.city.CityDAO;
import by.epam.green.library.dao.city.MySQLCityDAO;
import by.epam.green.library.dao.client.ClientDAO;
import by.epam.green.library.dao.client.MySQLClientDAO;
import by.epam.green.library.dao.contacts.ContactInfoDAO;
import by.epam.green.library.dao.contacts.MySQLContactInfoDAO;
import by.epam.green.library.dao.country.CountryDAO;
import by.epam.green.library.dao.country.MySQLCountryDAO;
import by.epam.green.library.dao.genre.GenreDAO;
import by.epam.green.library.dao.genre.MySQLGenreDAO;
import by.epam.green.library.dao.order.MySQLOrderDAO;
import by.epam.green.library.dao.order.OrderDAO;
import by.epam.green.library.dao.publisher.MySQLPublisherDAO;
import by.epam.green.library.dao.publisher.PublisherDAO;
import by.epam.green.library.dao.search.BookSearchDAO;
import by.epam.green.library.dao.search.MySQLBookSearchDAO;

/**
 * A factory for creating MySQL DAO objects.
 */
public class MySQLDAOFactory implements AbstractDAOFactory {

	/**
	* {@inheritDoc}
	*/
	@Override
	public ClientDAO createClientDAO(Connection connection) {
		return new MySQLClientDAO(connection);
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public BookDAO createBookDAO(Connection connection) {
		return new MySQLBookDAO(connection);
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public PublisherDAO createPublisherDAO(Connection connection) {
		return new MySQLPublisherDAO(connection);
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public AuthorDAO createAuthorDAO(Connection connection) {
		return new MySQLAuthorDAO(connection);
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public CityDAO createCityDAO(Connection connection) {
		return new MySQLCityDAO(connection);
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public ContactInfoDAO createContactInfoDAO(Connection connection) {
		return new MySQLContactInfoDAO(connection);
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public CountryDAO createCountryDAO(Connection connection) {
		return new MySQLCountryDAO(connection);
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public GenreDAO createDao(Connection connection) {
		return new MySQLGenreDAO(connection);			
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public OrderDAO createOrderDAO(Connection connection) {
		return new MySQLOrderDAO(connection);
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public BookSearchDAO createBookSearchDAO(Connection connection) {
		return new MySQLBookSearchDAO(connection);
	}
}
