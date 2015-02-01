package by.epam.green.library.dao.factory;

import by.epam.green.library.managers.SupportedDatabases;

/**
 * The Class DAOFactoryCreator provides
 */
public class DAOFactoryCreator {
	
	/** The factory. */
	private static AbstractDAOFactory factory = null;
	
	/**
	 * Instantiates a new DAO factory creator.
	 */
	private DAOFactoryCreator() {		
	}
	
	/**
	 * Gets the factory. Must be initialized first
	 * 
	 * @return the factory
	 */
	public static AbstractDAOFactory getFactory() {
		return factory;
	}
	
	/**
	 * Inits the factory by database name.
	 * @see by.epam.green.library.managers.SupportedDatabases
	 * @param database the database
	 */
	public static void initFactory(String database) {

		switch(SupportedDatabases.valueOf(database.toUpperCase())) {
		
		case MYSQL:
			factory = new MySQLDAOFactory();
			break;
			
		case ORALCE:
			
		default:
			throw new RuntimeException("Unknown database");
		}	
	}
}
