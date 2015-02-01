package by.epam.green.library.logic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import by.epam.green.library.connectpool.ConnectionPool;
import by.epam.green.library.dao.book.BookDAO;
import by.epam.green.library.dao.exception.DAOLogicException;
import by.epam.green.library.dao.exception.DAOTechnicalException;
import by.epam.green.library.dao.factory.DAOFactoryCreator;
import by.epam.green.library.dao.order.OrderDAO;
import by.epam.green.library.logic.exception.LogicException;
import by.epam.green.library.logic.exception.TechnicalException;
import by.epam.green.library.model.Order;
import by.epam.green.library.model.OrderStatus;
import by.epam.green.library.model.OrderType;

/**
 * The Class OrderOptions provides operations with orders.
 */
public class OrderOptions {

	/** The Constant LOGGER. */
	static final Logger LOGGER = Logger.getLogger(OrderOptions.class);

	/**
	 * Order book.
	 *
	 * @param clientId the client id
	 * @param bookId the book id
	 * @param orderType the order type
	 * @throws TechnicalException the technical exception
	 */
	public void orderBook(int clientId, int bookId, OrderType orderType)
			throws TechnicalException {

		Connection connection = ConnectionPool.getInstance().getConnection();
		OrderDAO orderDAO = DAOFactoryCreator.getFactory().createOrderDAO(connection);

		Order order = new Order();
		order.setClientId(clientId);
		order.setBookId(bookId);
		order.setOrderDate(new Date());
		order.setOrderType(orderType);
		order.setStatus(OrderStatus.REQUESTED);

		try {
			orderDAO.addOrder(order);
		} catch (DAOTechnicalException e) {
			throw new TechnicalException(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}

	}

	/**
	 * Show reader orders.
	 *
	 * @param readerId the reader id
	 * @param status the orders status
	 * @return the array list of orders
	 * @throws TechnicalException the technical exception
	 */
	public ArrayList<Order> showReaderOrders(int readerId, String status)
			throws TechnicalException {

		Connection connection = ConnectionPool.getInstance().getConnection();
		OrderDAO orderDAO = DAOFactoryCreator.getFactory().createOrderDAO(connection);
		ArrayList<Order> orders = new ArrayList<Order>();

		if (status == null || "ALL".equals(status)) {

			try {
				orders = orderDAO.findOrdersByClientId(readerId);
			} catch (DAOTechnicalException e) {
				throw new TechnicalException(e);
			} finally {
				ConnectionPool.getInstance().returnConnection(connection);
			}

		} else {

			try {
				orders = orderDAO.findOrdersByClientIdAndStatus(readerId, OrderStatus.valueOf(status));
			} catch (DAOTechnicalException e) {
				throw new TechnicalException(e);
			} finally {
				ConnectionPool.getInstance().returnConnection(connection);
			}
		}

		return orders;
	}

	/**
	 * Grant the book.
	 *
	 * @param orderId the order id
	 * @param bookId the book id
	 * @param returnDate the return date
	 * @throws TechnicalException the technical exception
	 * @throws LogicException the logic exception
	 */
	public void grant(int orderId, int bookId, Date returnDate)
			throws TechnicalException, LogicException {

		Connection connection = ConnectionPool.getInstance().getConnection();
		OrderDAO orderDAO = DAOFactoryCreator.getFactory().createOrderDAO(connection);
		BookDAO bookDAO = DAOFactoryCreator.getFactory().createBookDAO(connection);

		try {
			bookDAO.grantBook(bookId);
			orderDAO.setStatus(OrderStatus.GRANTED, orderId);
			orderDAO.setReturnDate(returnDate, orderId);
		} catch (DAOTechnicalException e) {
			throw new TechnicalException(e);
		} catch (DAOLogicException e) {
			throw new LogicException("Requested book is already granted");
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}
	}

	/**
	 * Reject order.
	 *
	 * @param orderId the order id
	 * @throws TechnicalException the technical exception
	 */
	public void reject(int orderId) throws TechnicalException {

		Connection connection = ConnectionPool.getInstance().getConnection();
		OrderDAO orderDAO = DAOFactoryCreator.getFactory().createOrderDAO(connection);

		try {
			orderDAO.setStatus(OrderStatus.REJECTED, orderId);
		} catch (DAOTechnicalException e) {
			throw new TechnicalException(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}
	}

	/**
	 * Return book.
	 *
	 * @param orderId the order id
	 * @param bookId the book id
	 * @throws TechnicalException the technical exception
	 */
	public void returnBook(int orderId, int bookId) throws TechnicalException {

		Connection connection = ConnectionPool.getInstance().getConnection();
		OrderDAO orderDAO = DAOFactoryCreator.getFactory().createOrderDAO(connection);
		BookDAO bookDAO = DAOFactoryCreator.getFactory().createBookDAO(connection);

		try {
			bookDAO.setAvailable(bookId, true);
			orderDAO.setStatus(OrderStatus.RETURNED, orderId);
		} catch (DAOTechnicalException e) {
			throw new TechnicalException(e);
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}

	}
}
