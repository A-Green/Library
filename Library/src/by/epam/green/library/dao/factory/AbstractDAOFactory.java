package by.epam.green.library.dao.factory;

import java.sql.Connection;

import by.epam.green.library.dao.author.AuthorDAO;
import by.epam.green.library.dao.book.BookDAO;
import by.epam.green.library.dao.city.CityDAO;
import by.epam.green.library.dao.client.ClientDAO;
import by.epam.green.library.dao.contacts.ContactInfoDAO;
import by.epam.green.library.dao.country.CountryDAO;
import by.epam.green.library.dao.genre.GenreDAO;
import by.epam.green.library.dao.order.OrderDAO;
import by.epam.green.library.dao.publisher.PublisherDAO;
import by.epam.green.library.dao.search.BookSearchDAO;

/**
 * A factory for creating AbstractDAO objects.
 */
public interface AbstractDAOFactory {

	/**
	 * Creates a new ClientDAO object.
	 *
	 * @param connection the connection
	 * @return the client DAO
	 */
	ClientDAO createClientDAO(Connection connection);
	
	/**
	 * Creates a new BookDAO object.
	 *
	 * @param connection the connection
	 * @return the book DAO
	 */
	BookDAO createBookDAO(Connection connection);
	
	/**
	 * Creates a new PublisherDAO object.
	 *
	 * @param connection the connection
	 * @return the publisher DAO
	 */
	PublisherDAO createPublisherDAO(Connection connection);
	
	/**
	 * Creates a new AuthorDAO object.
	 *
	 * @param connection the connection
	 * @return the author DAO
	 */
	AuthorDAO createAuthorDAO(Connection connection);
	
	/**
	 * Creates a new CityDAO object.
	 *
	 * @param connection the connection
	 * @return the city DAO
	 */
	CityDAO createCityDAO(Connection connection);
	
	/**
	 * Creates a new ContactInfoDAO object.
	 *
	 * @param connection the connection
	 * @return the contact info DAO
	 */
	ContactInfoDAO createContactInfoDAO(Connection connection);
	
	/**
	 * Creates a new CountryDAO object.
	 *
	 * @param connection the connection
	 * @return the country DAO
	 */
	CountryDAO createCountryDAO(Connection connection);
	
	/**
	 * Creates a new GenreDAO object.
	 *
	 * @param connection the connection
	 * @return the genre DAO
	 */
	GenreDAO createDao(Connection connection);
	
	/**
	 * Creates a new OrderDAO object.
	 *
	 * @param connection the connection
	 * @return the order DAO
	 */
	OrderDAO createOrderDAO(Connection connection);
	
	/**
	 * Creates a new BookSearchDAO object.
	 *
	 * @param connection the connection
	 * @return the book search DAO
	 */
	BookSearchDAO createBookSearchDAO(Connection connection);

}
