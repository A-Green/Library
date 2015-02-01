package by.epam.green.library.logic;

import java.sql.Connection;
import java.util.ArrayList;

import by.epam.green.library.connectpool.ConnectionPool;
import by.epam.green.library.dao.client.ClientDAO;
import by.epam.green.library.dao.exception.DAOTechnicalException;
import by.epam.green.library.dao.factory.DAOFactoryCreator;
import by.epam.green.library.logic.exception.TechnicalException;
import by.epam.green.library.model.Client;
import by.epam.green.library.model.OrderStatus;

/**
 * The Class ClientsOptions provides operations with clients 
 */
public class ClientsOptions {
	
	/**
	 * Show clients by order status.
	 *
	 * @param status the status
	 * @return the array list of clients
	 * @throws TechnicalException the technical exception
	 */
	public ArrayList<Client> showClientsByOrderStatus(OrderStatus status) throws TechnicalException{
		
		Connection connection = ConnectionPool.getInstance().getConnection();
		ClientDAO clientDAO = DAOFactoryCreator.getFactory().createClientDAO(connection);
		ArrayList<Client> clients = new ArrayList<Client>();

		try {
			clients = clientDAO.findClientsByOrderStatus(status);
		} catch (DAOTechnicalException e) {
			throw new TechnicalException(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}

		return clients; 
	}
	
	/**
	 * Show clients by name.
	 *
	 * @param name the client name
	 * @return the array list of clients
	 * @throws TechnicalException the technical exception
	 */
	public ArrayList<Client> showClientsByName(String name) throws TechnicalException {
		
		Connection connection = ConnectionPool.getInstance().getConnection();
		ClientDAO clientDAO = DAOFactoryCreator.getFactory().createClientDAO(connection);
		ArrayList<Client> clients = new ArrayList<Client>();

		try {
			clients = clientDAO.findClientsByName(name);
		} catch (DAOTechnicalException e) {
			throw new TechnicalException(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}

		return clients;
	}
}
