package by.epam.green.tests.connectpool;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;

import by.epam.green.library.connectpool.ConnectionPool;

public class ConnectionPoolTest {
	
	@BeforeClass
	public static void initTest() {
		
		try {	
			ConnectionPool.getInstance();
		} catch (Exception e) {
			Assume.assumeNoException(e);
		}	
	}
	
	@Test
	public void getConnectionTest() {
		
		Connection connection = ConnectionPool.getInstance().getConnection();	
		Assert.assertNotNull(connection);
	}
	
	@Test
	public void returnConnectionTest() {
		
		try {
	
		Connection connection = ConnectionPool.getInstance().getConnection();		
		ConnectionPool.getInstance().returnConnection(connection);
		
		} catch (Exception e) {
			Assume.assumeNoException(e);
		}
		
	}
	@AfterClass
	public static void returnAllConenctionsTest() {
		
		Connection connection1 = ConnectionPool.getInstance().getConnection();		
		Assert.assertNotNull(connection1);
		
		Connection connection2 = ConnectionPool.getInstance().getConnection();		
		Assert.assertNotNull(connection2);
		
		ConnectionPool.getInstance().returnAllConnections();
		
		Connection connection3 = ConnectionPool.getInstance().getConnection();
		Assert.assertNull(connection3);
	}

}
