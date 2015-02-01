package by.epam.green.library.dao.client;

import java.util.ArrayList;

import by.epam.green.library.dao.exception.DAOLogicException;
import by.epam.green.library.dao.exception.DAOTechnicalException;
import by.epam.green.library.model.Client;
import by.epam.green.library.model.OrderStatus;

/**
 * The Interface ClientDAO.
 */
public interface ClientDAO {

	/**
	 * Adds the new client.
	 *
	 * @param client the client
	 * @return the id of added client
	 * @throws DAOTechnicalException the DAO technical exception
	 * @throws DAOLogicException the DAO logic exception if login is already in use
	 */
	int addNewClient(Client client) throws DAOTechnicalException, DAOLogicException;
	
	/**
	 * Finds client by login.
	 *
	 * @param login the login
	 * @return the client
	 * @throws DAOTechnicalException the DAO technical exception
	 */
	Client findClientByLogin(String login) throws DAOTechnicalException;
	
	/**
	 * Find client by login and pass.
	 *
	 * @param login the login
	 * @param pass the pass
	 * @return the client
	 * @throws DAOTechnicalException the DAO technical exception
	 */
	Client findClientByLoginAndPass(String login, long pass) throws DAOTechnicalException;
	
	/**
	 * Find clients by order status.
	 *
	 * @param status the status
	 * @return the array list of clients
	 * @throws DAOTechnicalException the DAO technical exception
	 */
	ArrayList<Client> findClientsByOrderStatus(OrderStatus status) throws DAOTechnicalException;
	
	/**
	 * Find clients by name.
	 *
	 * @param name the name
	 * @return the array list of clients with such name
	 * @throws DAOTechnicalException the DAO technical exception
	 */
	ArrayList<Client> findClientsByName(String name) throws DAOTechnicalException;

}
