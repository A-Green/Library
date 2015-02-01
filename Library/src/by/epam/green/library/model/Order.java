package by.epam.green.library.model;

import java.util.Date;

/**
 * The Class Order represents order for book
 */
public class Order extends Entity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The ordered book id. */
	private int bookId;
	
	/** The client id. */
	private int clientId;
	
	/** The ordered book name. */
	private String bookName;
	
	/** The client name. */
	private String clientName;
	
	/** The order type. */
	private OrderType orderType;
	
	/** The order status. */
	private OrderStatus status;
	
	/** The order date. */
	private Date orderDate;
	
	/** The return date. */
	private Date returnDate;

	/**
	 * Gets the book id.
	 *
	 * @return the book id
	 */
	public int getBookId() {
		return bookId;
	}

	/**
	 * Sets the book id.
	 *
	 * @param bookId the new book id
	 */
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	/**
	 * Gets the client id.
	 *
	 * @return the client id
	 */
	public int getClientId() {
		return clientId;
	}

	/**
	 * Sets the client id.
	 *
	 * @param clientId the new client id
	 */
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	/**
	 * Gets the book name.
	 *
	 * @return the book name
	 */
	public String getBookName() {
		return bookName;
	}

	/**
	 * Sets the book name.
	 *
	 * @param bookName the new book name
	 */
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	/**
	 * Gets the client name.
	 *
	 * @return the client name
	 */
	public String getClientName() {
		return clientName;
	}

	/**
	 * Sets the client name.
	 *
	 * @param clientName the new client name
	 */
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	/**
	 * Gets the order type.
	 *
	 * @return the order type
	 */
	public OrderType getOrderType() {
		return orderType;
	}

	/**
	 * Sets the order type.
	 *
	 * @param orderType the new order type
	 */
	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}
	
	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public OrderStatus getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
	/**
	 * Gets the order date.
	 *
	 * @return the order date
	 */
	public Date getOrderDate() {
		return orderDate;
	}

	/**
	 * Sets the order date.
	 *
	 * @param orderDate the new order date
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	/**
	 * Gets the return date.
	 *
	 * @return the return date
	 */
	public Date getReturnDate() {
		return returnDate;
	}

	/**
	 * Sets the return date.
	 *
	 * @param returnDate the new return date
	 */
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	
	/**
	* {@inheritDoc}
	*/
	@Override
	public String toString(){
		
		StringBuilder  srtBuilder = new StringBuilder();
		srtBuilder.append("Id = ").append(getId())
		.append(" book id = ").append(bookId)
		.append(" book title : ").append(bookName)
		.append(" client id = ").append(clientId)
		.append(" client name: ").append(clientName)
		.append(" type: ").append(orderType)
		.append(" status: ").append(status)
		.append(" order date: ").append(orderDate)
		.append(" retrun date: ").append(orderType);
		
		return srtBuilder.toString();
	}
	
	/**
	* {@inheritDoc}
	*/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + bookId;
		result = prime * result
				+ ((bookName == null) ? 0 : bookName.hashCode());
		result = prime * result + clientId;
		result = prime * result
				+ ((clientName == null) ? 0 : clientName.hashCode());
		result = prime * result
				+ ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result
				+ ((orderType == null) ? 0 : orderType.hashCode());
		result = prime * result
				+ ((returnDate == null) ? 0 : returnDate.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	/**
	* {@inheritDoc}
	*/
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (bookId != other.bookId)
			return false;
		if (bookName == null) {
			if (other.bookName != null)
				return false;
		} else if (!bookName.equals(other.bookName))
			return false;
		if (clientId != other.clientId)
			return false;
		if (clientName == null) {
			if (other.clientName != null)
				return false;
		} else if (!clientName.equals(other.clientName))
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		if (orderType != other.orderType)
			return false;
		if (returnDate == null) {
			if (other.returnDate != null)
				return false;
		} else if (!returnDate.equals(other.returnDate))
			return false;
		if (status != other.status)
			return false;
		return true;
	}
	
	/**
	* {@inheritDoc}
	*/
	@Override
	public Order clone() throws CloneNotSupportedException {
		Order clone = (Order) super.clone();
		clone.orderDate = (Date) orderDate.clone();
		clone.returnDate = (Date) returnDate.clone();
		return clone;
	}

}
