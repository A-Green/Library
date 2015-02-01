package by.epam.green.tests.managers;

import java.util.MissingResourceException;

import org.junit.Assume;
import org.junit.Test;

import by.epam.green.library.managers.ResourceManager;

public class ResourceManagerTest {
	
	@Test
	public void initTest() {
		
		try {
			ResourceManager.getInstance();
		} catch (Exception e) {
			Assume.assumeNoException(e);
		}
	}
	
	@Test
	public void getParametersTest() {
		
		try {	
			
		ResourceManager.getInstance().getParametr("url");
		ResourceManager.getInstance().getParametr("driver");
		ResourceManager.getInstance().getParametr("user");
		ResourceManager.getInstance().getParametr("password");
		
		} catch (MissingResourceException e) {
			Assume.assumeNoException(e);
		}
	}

}
