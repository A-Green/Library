package by.epam.green.library.dao.order;

import java.util.ArrayList;
import java.util.Date;

import by.epam.green.library.dao.exception.DAOTechnicalException;
import by.epam.green.library.model.Order;
import by.epam.green.library.model.OrderStatus;

/**
 * The Interface OrderDAO.
 */
public interface OrderDAO {

	 /**
 	 * Find orders by client id.
 	 *
 	 * @param id the client id
 	 * @return the array list client orders
 	 * @throws DAOTechnicalException the DAO technical exception
 	 */
 	ArrayList<Order> findOrdersByClientId(int id) throws DAOTechnicalException;
	 
 	/**
 	 * Find orders by status.
 	 *
 	 * @param status the orders status
 	 * @return the array list of orders
 	 * @throws DAOTechnicalException the DAO technical exception
 	 */
 	ArrayList<Order> findOrdersByStatus(OrderStatus status) throws DAOTechnicalException;
	 
 	/**
 	 * Find orders by client id and status.
 	 *
 	 * @param id the client id
 	 * @param status the status
 	 * @return the array list orders
 	 * @throws DAOTechnicalException the DAO technical exception
 	 */
 	ArrayList<Order> findOrdersByClientIdAndStatus(int id, OrderStatus status) throws DAOTechnicalException;
	 
 	/**
 	 * Sets the return date to order.
 	 *
 	 * @param date the return date
 	 * @param id the id if order
 	 * @throws DAOTechnicalException the DAO technical exception
 	 */
 	void setReturnDate(Date date, int id) throws DAOTechnicalException;
	 
 	/**
 	 * Sets the status.
 	 *
 	 * @param status the order status
 	 * @param id the order id
 	 * @throws DAOTechnicalException the DAO technical exception
 	 */
 	void setStatus(OrderStatus status, int id) throws DAOTechnicalException;
	 
 	/**
 	 * Adds new order.
 	 *
 	 * @param order the order
 	 * @throws DAOTechnicalException the DAO technical exception
 	 */
 	void addOrder(Order order) throws DAOTechnicalException;
}
