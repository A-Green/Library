package by.epam.green.library.logic;

import java.sql.Connection;

import by.epam.green.library.connectpool.ConnectionPool;
import by.epam.green.library.dao.client.ClientDAO;
import by.epam.green.library.dao.exception.DAOLogicException;
import by.epam.green.library.dao.exception.DAOTechnicalException;
import by.epam.green.library.dao.factory.DAOFactoryCreator;
import by.epam.green.library.logic.exception.LogicException;
import by.epam.green.library.logic.exception.TechnicalException;
import by.epam.green.library.model.Client;

/**
 * The Class Registration provides registration logic
 */
public class Registration {

	/**
	 * Registration of new client
	 *
	 * @param newClient the new client
	 * @throws LogicException the logic exception if client with such login is already present
	 * @throws TechnicalException the technical exception
	 */
	public void registration(Client newClient) throws LogicException, TechnicalException {

		Connection connection = ConnectionPool.getInstance().getConnection();
		ClientDAO clientDAO = DAOFactoryCreator.getFactory().createClientDAO(connection);

		try {
			clientDAO.addNewClient(newClient);
		} catch (DAOTechnicalException e) {
			throw new TechnicalException(e);
		} catch (DAOLogicException e) {
			throw new LogicException("Login is already in use");
		}
	}
}
