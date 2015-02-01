package by.epam.green.library.dao.order;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import by.epam.green.library.dao.AbstractDAO;
import by.epam.green.library.dao.exception.DAOTechnicalException;
import by.epam.green.library.model.Order;
import by.epam.green.library.model.OrderStatus;
import by.epam.green.library.model.OrderType;

/**
 * The Class MySQLOrderDAO.
 */
public class MySQLOrderDAO extends AbstractDAO implements OrderDAO{

	private Connection connection;
	
	private static final String GET_ORDERS_BY_CLIENT_ID = 
			"SELECT orders.id, client.id, book.id, book.title, orders.orderdate, orders.type, orders.status, client.name, orders.returndate "
			+ "FROM orders JOIN client ON orders.client_id = client.id "
			+ "JOIN book ON book_id = book.id WHERE client.id = ? ORDER BY orderdate DESC";
	
	private static final String GET_ORDERS_BY_STATUS = 
			"SELECT orders.id, client.id, book.id, book.title, orders.orderdate, orders.type, orders.status, client.name, orders.returndate  "
			+ "FROM orders JOIN client ON orders.client_id = client.id "
			+ "JOIN book ON book_id = book.id WHERE status = ? ORDER BY orderdate DESC";
	
	private static final String GET_ORDERS_BY_STATUS_AND_CLIENT_ID = 
			"SELECT orders.id, client.id, book.id, book.title, orders.orderdate, orders.type, orders.status, client.name, orders.returndate  "
			+ "FROM orders JOIN client ON orders.client_id = client.id "
			+ "JOIN book ON book_id = book.id WHERE client.id = ? AND status = ? ORDER BY orderdate DESC";
	
	private static final String ADD_ORDER =
			"INSERT INTO orders (client_id, book_id, orderdate, type, status) "
			+ "VALUES (?, ?, ?, ?, ?)";
	
	private static final String CHANGE_STATUS_BY_ID = 
			"UPDATE orders SET status = ?, orderdate = ? WHERE id = ?";
	
	private static final String SET_RETURN_DATE = 
			"UPDATE orders SET returndate = ? WHERE id = ?";
	
	/**
	 * Instantiates a new MySQL order DAO.
	 *
	 * @param connection the connection
	 */
	public MySQLOrderDAO(Connection connection) {
		this.connection = connection;
	}
	
	/**
	* {@inheritDoc}
	*/
	@Override
	public ArrayList<Order> findOrdersByClientId(int id) throws DAOTechnicalException {
		ArrayList<Order> result = new ArrayList<Order>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			statement = connection.prepareStatement(GET_ORDERS_BY_CLIENT_ID);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				
				Order order = new Order();
				order.setId(resultSet.getInt(1));
				order.setClientId(resultSet.getInt(2));
				order.setBookId(resultSet.getInt(3));
				order.setBookName(resultSet.getString(4));
				order.setOrderDate(resultSet.getDate(5));
				
				String orderType = resultSet.getString(6);
				order.setOrderType(OrderType.valueOf(orderType));
				
				String status = resultSet.getString(7);
				order.setStatus(OrderStatus.valueOf(status));
				order.setClientName(resultSet.getString(8));
				order.setReturnDate(resultSet.getDate(9));
				result.add(order);
			}
		} catch (SQLException e) {
			throw new DAOTechnicalException(e);
		} finally {
			closeStatement(statement);
		}

		return result;
	}
	
	/**
	* {@inheritDoc}
	*/
	public void addOrder(Order order) throws DAOTechnicalException {
		
		PreparedStatement statement = null;
		
		try {
			statement = connection.prepareStatement(ADD_ORDER);
			statement.setInt(1, order.getClientId());
			statement.setInt(2, order.getBookId());
			statement.setDate(3, new Date(order.getOrderDate().getTime()));
			statement.setString(4, order.getOrderType().toString());
			statement.setString(5, order.getStatus().toString());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DAOTechnicalException(e);
		} finally {
			closeStatement(statement);
		}
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public ArrayList<Order> findOrdersByStatus(OrderStatus status) throws DAOTechnicalException {
		ArrayList<Order> result = new ArrayList<Order>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			statement = connection.prepareStatement(GET_ORDERS_BY_STATUS);
			statement.setString(1, status.toString());
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				
				Order order = new Order();
				order.setId(resultSet.getInt(1));
				order.setClientId(resultSet.getInt(2));
				order.setBookId(resultSet.getInt(3));
				order.setBookName(resultSet.getString(4));
				order.setOrderDate(resultSet.getDate(5));
				
				String orderType = resultSet.getString(6);
				order.setOrderType(OrderType.valueOf(orderType));
				
				String stat = resultSet.getString(7);
				order.setStatus(OrderStatus.valueOf(stat));
				order.setClientName(resultSet.getString(8));
				order.setReturnDate(resultSet.getDate(9));
				result.add(order);
			}
		} catch (SQLException e) {
			throw new DAOTechnicalException(e);
		} finally {
			closeStatement(statement);
		}
		return result;
	}
	
	/**
	* {@inheritDoc}
	*/
	public void setStatus(OrderStatus status, int id) throws DAOTechnicalException {
		
		PreparedStatement statement = null;
		
		try {
			statement = connection.prepareStatement(CHANGE_STATUS_BY_ID);
			statement.setString(1, status.toString());
			statement.setDate(2, new Date(System.currentTimeMillis()));
			statement.setInt(3, id);
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DAOTechnicalException(e);
		} finally {
			closeStatement(statement);
		}

	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public ArrayList<Order> findOrdersByClientIdAndStatus(int id, OrderStatus status) throws DAOTechnicalException {
		ArrayList<Order> result = new ArrayList<Order>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			statement = connection.prepareStatement(GET_ORDERS_BY_STATUS_AND_CLIENT_ID);
			statement.setInt(1, id);
			statement.setString(2, status.toString());
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				
				Order order = new Order();
				order.setId(resultSet.getInt(1));
				order.setClientId(resultSet.getInt(2));
				order.setBookId(resultSet.getInt(3));
				order.setBookName(resultSet.getString(4));
				order.setOrderDate(resultSet.getDate(5));
				
				String orderType = resultSet.getString(6);
				order.setOrderType(OrderType.valueOf(orderType));
				
				String stat = resultSet.getString(7);
				order.setStatus(OrderStatus.valueOf(stat));
				order.setClientName(resultSet.getString(8));
				order.setReturnDate(resultSet.getDate(9));
				result.add(order);
			}
		} catch (SQLException e) {
			throw new DAOTechnicalException(e);
		} finally {
			closeStatement(statement);
		}
		return result;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public void setReturnDate(java.util.Date date, int id) throws DAOTechnicalException {
		
		PreparedStatement statement = null;
		
		try {
			statement = connection.prepareStatement(SET_RETURN_DATE);
			statement.setDate(1, new Date(date.getTime()));
			statement.setInt(2, id);
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DAOTechnicalException(e);
		} finally {
			closeStatement(statement);
		}
	}
}
