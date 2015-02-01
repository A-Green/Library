package by.epam.green.library.logic;

import java.sql.Connection;

import by.epam.green.library.connectpool.ConnectionPool;
import by.epam.green.library.dao.client.ClientDAO;
import by.epam.green.library.dao.exception.DAOTechnicalException;
import by.epam.green.library.dao.factory.DAOFactoryCreator;
import by.epam.green.library.logic.exception.LogicException;
import by.epam.green.library.logic.exception.TechnicalException;
import by.epam.green.library.model.Client;

/**
 * The Class Authorization provides authorization logic
 */
public class Authorization {
	
	/**
	 * Log in.
	 *
	 * @param login the login
	 * @param pass the pass
	 * @return the client found by login and pass
	 * @throws TechnicalException the technical exception
	 * @throws LogicException the logic exception if client not found
	 */
	public static Client logIn(String login, long pass) throws TechnicalException, LogicException {
		
		Client client = null;
		
		Connection connection = ConnectionPool.getInstance().getConnection();
		ClientDAO clientDAO = DAOFactoryCreator.getFactory().createClientDAO(connection);

		try {
			client = clientDAO.findClientByLoginAndPass(login, pass);
		} catch (DAOTechnicalException e) {
			throw new TechnicalException(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}
		
		if (client == null) {
			throw new LogicException("There is no such user");
		}

		return client;
	}
	
}
