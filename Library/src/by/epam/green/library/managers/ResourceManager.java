package by.epam.green.library.managers;

import java.util.ResourceBundle;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

/**
 * The Class ResourceManager/
 */
public final class ResourceManager {
	
	/** The logger. */
	static Logger logger = Logger.getLogger(ResourceManager.class);
	
	/** The instance. */
	private static ResourceManager instance = null;
	
	/** The lock. */
	private static ReentrantLock lock = new ReentrantLock();
	
	/** The resource bundle. */
	private static ResourceBundle resourceBundle;
	
	/** The resource bundle name. */
	private static String BUNDLE_NAME = "properties.resources";
	
	/**
	 * Instantiates a new resource manager.
	 */
	private ResourceManager() {
		
	}
	
	/**
	 * Gets the single instance of ResourceManager.
	 *
	 * @return single instance of ResourceManager
	 */
	public static ResourceManager getInstance() {

		if (instance == null) {
			try {

				lock.lock();

				if (instance == null) {
					instance = new ResourceManager();
					resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
				}
			} finally {
				lock.unlock();
			}

		}
		return instance;
	}
	
	/**
	 * Gets the properties parameter.
	 *
	 * @param key the key
	 * @return the parameter
	 */
	public String getParametr(String key) {
		return (String) resourceBundle.getObject(key);
	}

}
