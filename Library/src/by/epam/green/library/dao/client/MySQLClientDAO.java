package by.epam.green.library.dao.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import by.epam.green.library.dao.AbstractDAO;
import by.epam.green.library.dao.contacts.ContactInfoDAO;
import by.epam.green.library.dao.contacts.MySQLContactInfoDAO;
import by.epam.green.library.dao.exception.DAOLogicException;
import by.epam.green.library.dao.exception.DAOTechnicalException;
import by.epam.green.library.model.Client;
import by.epam.green.library.model.ClientType;
import by.epam.green.library.model.OrderStatus;

/**
 * The Class MySQLClientDAO.
 */
public class MySQLClientDAO extends AbstractDAO implements ClientDAO {
	
	private Connection connection;
	
	private static final String GET_CLIENT_BY_LOGIN_AND_PASSWORD = 
			"SELECT * FROM client WHERE login = ? AND password = ?";

	private static final String GET_CLIENTS_BY_ORDERS_STATUS = 
			"SELECT client.id, client.name, contact_info_id "
			+ "FROM client JOIN orders ON client.id = orders.client_id WHERE status = ? GROUP BY client_id";

	private static final String GET_CLIENTS_BY_NAME = 
			"SELECT client.id, client.name, contact_info_id "
			+ "FROM client WHERE name LIKE ?";

	private static final String ADD_NEW_CLIENT = 
			"INSERT INTO client (name, login, password, access, contact_info_id) VALUES(?,?,?,?,?)";
	
	private static final String GET_CLIENT_BY_LOGIN = "SELECT * FROM client WHERE login = ?";
	
	/**
	 * Instantiates a new MySQL client DAO.
	 *
	 * @param connection the connection
	 */
	public MySQLClientDAO(Connection connection) {
		this.connection = connection;
	}

	/**
	* {@inheritDoc}
	*/
	public Client findClientByLoginAndPass(String login, long pass) 
			throws DAOTechnicalException {
		
		Client client = null;
		
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			statement = connection.prepareStatement(GET_CLIENT_BY_LOGIN_AND_PASSWORD);
			statement.setString(1, login);
			statement.setLong(2, pass);	
			result = statement.executeQuery();
			
			if (result.next()) {
					
				client = new Client();
				client.setId(result.getInt(1));
				client.setLogin(login);
				client.setPass(pass);
				client.setName(result.getString(2));
				client.setRole(ClientType.valueOf(result.getString(5)));
			}
		} catch (SQLException e) {
			throw new DAOTechnicalException(e);
		} finally {
			closeStatement(statement);		
		}
		
		return client;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public ArrayList<Client> findClientsByOrderStatus(OrderStatus status) throws DAOTechnicalException {

		ResultSet result = null;
		PreparedStatement statement = null;
		ArrayList<Client> clients = new ArrayList<Client>();
		ContactInfoDAO infoDAO = new MySQLContactInfoDAO(connection);
		
		try {
			statement = connection.prepareStatement(GET_CLIENTS_BY_ORDERS_STATUS);
			statement.setString(1, status.toString());
			result = statement.executeQuery();
			
			while (result.next()) {					
				Client client = new Client();
				client.setId(result.getInt(1));
				client.setName(result.getString(2));
				int infoId = result.getInt(3);
				client.setContactInfo(infoDAO.findContactInfoById(infoId));
				clients.add(client);
			}
		} catch (SQLException e) {
			throw new DAOTechnicalException(e);
		} finally {
			closeStatement(statement);		
		}
		
		return clients;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public ArrayList<Client> findClientsByName(String name) throws DAOTechnicalException {

		ResultSet result = null;
		PreparedStatement statement = null;
		ArrayList<Client> clients = new ArrayList<Client>();
		ContactInfoDAO infoDAO = new MySQLContactInfoDAO(connection);
		
		try {
			statement = connection.prepareStatement(GET_CLIENTS_BY_NAME);
			statement.setString(1,"%" + name + "%");
			result = statement.executeQuery();
			
			while (result.next()) {					
				Client client = new Client();
				client.setId(result.getInt(1));
				client.setName(result.getString(2));
				int infoId = result.getInt(3);
				client.setContactInfo(infoDAO.findContactInfoById(infoId));
				clients.add(client);
			}
		} catch (SQLException e) {
			throw new DAOTechnicalException(e);
		} finally {
			closeStatement(statement);		
		}
		
		return clients;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public int addNewClient(Client client) throws DAOTechnicalException, DAOLogicException {
		
		int clientId = 0;
		ResultSet result = null;
		PreparedStatement statement = null;
		ContactInfoDAO infoDAO = new MySQLContactInfoDAO(connection);
		
		try {
			
			Client existed = findClientByLogin(client.getLogin());
			
			if (existed != null) {
				throw new DAOLogicException();
			}
			
			int infoId = infoDAO.addContactInfo(client.getContactInfo());		
			statement = connection.prepareStatement(ADD_NEW_CLIENT, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1,client.getName());
			statement.setString(2, client.getLogin());
			statement.setLong(3, client.getPass());
			statement.setString(4, client.getRole().toString());
			statement.setInt(5, infoId);
			statement.executeUpdate();
			result = statement.getGeneratedKeys();
			
			if (result.next()) {					
				clientId = result.getInt(1);
			}
		} catch (SQLException e) {
			throw new DAOTechnicalException(e);
		} finally {
			closeStatement(statement);		
		}
		
		return clientId;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public Client findClientByLogin(String login) throws DAOTechnicalException {
		ResultSet result = null;
		PreparedStatement statement = null;
		Client client = null;
		
		try {
			statement = connection.prepareStatement(GET_CLIENT_BY_LOGIN);
			statement.setString(1, login);
			result = statement.executeQuery();
			
			if (result.next()) {					
				client = new Client();
				client.setId(result.getInt(1));
				client.setName(result.getString(2));
			}
		} catch (SQLException e) {
			throw new DAOTechnicalException(e);
		} finally {
			closeStatement(statement);		
		}
		
		return client;
	}
}
