package by.epam.green.library.dao;

import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

/**
 * The Class abstract DAO the base class for all DAO
 */
public abstract class AbstractDAO {
	
	/** The Constant LOGGER. */
	public static final Logger LOGGER = Logger.getLogger(AbstractDAO.class);

	/**
	 * Closes statement.
	 *
	 * @param statement the statement
	 */
	public void closeStatement(Statement statement) {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			LOGGER.error("Can not close statement", e);
		}
	}
}
